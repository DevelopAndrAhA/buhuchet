package kg.dor;

import kg.dor.models.*;
import kg.dor.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class RestController {

	@Autowired
	CrudService crudService;


	@RequestMapping(value = "skladskoi-uchet",method = RequestMethod.GET)
	public String printWelcome() {
		return "hello";
	}

	@RequestMapping(value = "sign-in",method = RequestMethod.POST)
	public String signIn(ModelMap model,HttpServletRequest httpServletRequest) {
		String login = httpServletRequest.getParameter("login");
		String password = httpServletRequest.getParameter("password");
		HttpSession httpSession = httpServletRequest.getSession();
		boolean b = crudService.authorization(login,password,httpServletRequest);
		if(!b){
			model.addAttribute("errorSignIn","errorSignIn");
			return "hello";
		}else{
			List<Order>l = crudService.getOrders();
			List<Client> clients = null;

			if(l!=null){
				long [] cl_ids = new long[l.size()];
				if(l!=null && l.size()!=0){
					model.addAttribute("orders",l);
					for(int i = 0;i<l.size();i++){
						cl_ids[i] = clients.get(i).getCl_id();
					}
					clients = crudService.getClients(cl_ids);
					model.addAttribute("clients",clients);
					httpSession.setAttribute("orderSize", l.size());
					httpSession.setAttribute("clickedOrder",false);
				}
			}

			return "main";
		}


	}



	@RequestMapping(value="orders",method = RequestMethod.GET)
	public String mainPage(ModelMap model,HttpServletRequest httpServletRequest) {
		List<Order>l = crudService.getOrders();
		List<Client> clients = new ArrayList<>();
		if(l!=null && l.size()!=0){
			model.addAttribute("orders",l);
			model.addAttribute("orders",l);
			for(int i = 0;i<l.size();i++){
				clients.add(crudService.getClient(l.get(i).getCl_id()));
			}
			model.addAttribute("clients",clients);
		}
		return "main";
	}



	@RequestMapping(value="couriers",method = RequestMethod.GET)
	public String couriers(ModelMap model,HttpServletRequest httpServletRequest) {
		List l = crudService.getCouriers();
		if(l!=null && l.size()!=0){
			model.addAttribute("couriers",l);
		}
		return "couriers";
	}


	@RequestMapping(value="courier",method = RequestMethod.GET)
	public String newcourier(ModelMap model,HttpServletRequest httpServletRequest,@RequestParam("cr_id")String cr_id) {
		if(cr_id!=null){
			if(!cr_id.equals("0")){
				model.addAttribute("courier", crudService.getCourier(Long.parseLong(cr_id)));
			}
		}
		return "new_courier";
	}

	@RequestMapping(value="savecourier",method = RequestMethod.POST)
	public String savecourier(ModelMap model,HttpServletRequest httpServletRequest) {
		Courier courier = new Courier();
		int cr_id =0;
		try{
			 cr_id = Integer.parseInt(httpServletRequest.getParameter("cr_id"));
		}catch (Exception e){}
		String login=httpServletRequest.getParameter("login");
		String password=httpServletRequest.getParameter("password");
		String fio=httpServletRequest.getParameter("fio");
		String phone=httpServletRequest.getParameter("phone");
		String blocked_status=httpServletRequest.getParameter("blocked_status");
		if(blocked_status==null){
			blocked_status = "unchecked";
		}else{
			blocked_status = "checked";
		}
		if(cr_id!=0){
			courier.setCr_id(cr_id);
			courier.setLogin(login);
			courier.setPassword(password);
			courier.setFio(fio);
			courier.setPhone(phone);
			courier.setBlocked_status(blocked_status);
			crudService.update(courier);
		}else{
			courier.setLogin(login);
			courier.setPassword(password);
			courier.setFio(fio);
			courier.setPhone(phone);
			courier.setBlocked_status(blocked_status);
			crudService.save(courier);
		}

		return "redirect:/couriers";
	}


	@RequestMapping(value="savedepartment",method = RequestMethod.POST)
	public String saveotdel(ModelMap model,HttpServletRequest httpServletRequest) {
		String name = httpServletRequest.getParameter("nazvanie");
		String otdel_id = httpServletRequest.getParameter("otdel_id");

		if(otdel_id!=null&&!otdel_id.isEmpty()&&!otdel_id.equals("0")){
			Otdel otdel = new Otdel();
			otdel.setName(name);
			otdel.setOtdel_id(Long.valueOf(otdel_id));
			crudService.update(otdel);
		}else if(name!=null&&!name.equals("")){
			Otdel otdel = new Otdel();
			otdel.setName(name);
			crudService.save(otdel);
		}
		return "redirect:/departments";
	}

	@RequestMapping(value="savechildtdel",method = RequestMethod.POST)
	public String saveChildOtdel(ModelMap model,HttpServletRequest httpServletRequest) {
		String otdel_id = httpServletRequest.getParameter("otdel_id");
		String child_otdel_id = httpServletRequest.getParameter("child_otdel_id");
		String name = httpServletRequest.getParameter("name");

		if(child_otdel_id!=null){
			if(child_otdel_id.equals("0")){
				ChildOtdel childOtdel = new ChildOtdel();
				childOtdel.setName(name);
				childOtdel.setParent_otdel(Long.parseLong(otdel_id));
				crudService.save(childOtdel);
			}else{
				ChildOtdel childOtdel = new ChildOtdel();
				childOtdel.setName(name);
				childOtdel.setParent_otdel(Long.parseLong(otdel_id));
				childOtdel.setChild_otdel_id(Long.parseLong(child_otdel_id));
				crudService.update(childOtdel);
			}
		}else{
			ChildOtdel childOtdel = new ChildOtdel();
			childOtdel.setName(name);
			childOtdel.setParent_otdel(Long.parseLong(otdel_id));
			childOtdel.setChild_otdel_id(Long.parseLong(child_otdel_id));
			crudService.update(childOtdel);
		}

		return "redirect:/newdepartment?otdel_id="+otdel_id;
	}



	@RequestMapping(value = "departments",method = RequestMethod.GET)
	public String otdel(ModelMap model) {
		List l = crudService.getOtdels();
		if(l!=null && l.size()!=0){
			model.addAttribute("otdels",l);
		}
		return "otdel";
	}



	@RequestMapping(value = "new_child_department",method = RequestMethod.GET)
	public String child_otdels(@RequestParam("otdel_id")String otdel_id,@RequestParam("child_department_id")String child_department_id,ModelMap model) {
		Otdel otdel = crudService.getOtdel(Long.parseLong(otdel_id));
		if(child_department_id!=null){
			if(!child_department_id.equals("0")){
				ChildOtdel childOtdel = crudService.getChildOtdel(Long.parseLong(child_department_id));
				if(childOtdel!=null){
					model.addAttribute("childOtdel",childOtdel);
				}
			}
		}

		model.addAttribute("otdel",otdel);

		return "child_department";
	}

	@RequestMapping(value = "newdepartment",method = RequestMethod.GET)
	public String newotdel(@RequestParam("otdel_id")String otdel_id,ModelMap model) {
		if(otdel_id!=null){
			if(!otdel_id.isEmpty()&&!otdel_id.equals(" ")){
				try{
					Otdel otdel =  crudService.getOtdel(Long.parseLong(otdel_id));
					model.addAttribute("otdel",otdel);
					List <ChildOtdel> childOtdels = crudService.getChildOtdels(otdel.getOtdel_id());
					if(childOtdels!=null){
						if(childOtdels.size()!=0){
							model.addAttribute("childOtdels",childOtdels);
						}
					}
				}catch (Exception e){}
			}
		}
		return "new_otdel";
	}



	@RequestMapping(value = "department_info",method = RequestMethod.GET)
	public String department_info(@RequestParam("otdel_id")String otdel_id,ModelMap model) {
		List l = crudService.getChildOtdels(Long.parseLong(otdel_id));
		if(l!=null && l.size()!=0){
			model.addAttribute("childOtdels",l);
		}
		List l2 = crudService.getClients(Long.parseLong(otdel_id));
		if(l2!=null && l2.size()!=0){
			model.addAttribute("clients",l2);
		}
		return "department_info";
	}



	@RequestMapping(value = "new_client",method = RequestMethod.GET)
	public String new_client(@RequestParam("otdel_id")String otdel_id,@RequestParam("child_department_id")String child_department_id,@RequestParam("client_id")String client_id,ModelMap model) {
		if(client_id!=null){
			if(!client_id.equals("0")){
				Client client = crudService.getClient(Long.parseLong(client_id));
				model.addAttribute("client",client);
			}
		}
		if(otdel_id!=null){
			if(!otdel_id.equals("0")){
				Otdel otdel = crudService.getOtdel(Long.parseLong(otdel_id));
				if(otdel!=null){
					model.addAttribute("otdel",otdel);
				}
			}
		}
		if(child_department_id!=null){
			if(!child_department_id.equals("0")){
				ChildOtdel childOtdel = crudService.getChildOtdel(Long.parseLong(child_department_id));
				if(childOtdel!=null){
					model.addAttribute("childOtdel",childOtdel);
				}
			}
		}
		return "new_client";
	}




	@RequestMapping(value = "save_client",method = RequestMethod.POST)
	public String save_client(HttpServletRequest httpServletRequest,ModelMap model) {
		String department_id = httpServletRequest.getParameter("department_id");
		String childOtdelId = httpServletRequest.getParameter("childOtdelId");
		String client_id = httpServletRequest.getParameter("client_id");
		String fio = httpServletRequest.getParameter("fio");
		String phone = httpServletRequest.getParameter("phone");
		String balance = httpServletRequest.getParameter("balance");

		Client client = new Client();
		if(childOtdelId!=null){
			if(!childOtdelId.isEmpty()){
				client.setOtdel_id(Long.parseLong(childOtdelId));
			}else{
				client.setOtdel_id(Long.parseLong(department_id));
			}
		}else{
			client.setOtdel_id(Long.parseLong(department_id));
		}


		if(client_id!=null){
			if(!client_id.equals("0")&&!client_id.isEmpty()){

				client.setCl_id(Long.parseLong(client_id));
				client.setFio(fio);
				client.setPhone(phone);
				client.setSumma_dolga(Float.parseFloat(balance));
				crudService.update(client);
				Otdel otdel = crudService.getOtdel(Long.parseLong(department_id));
				if(otdel!=null){
					model.addAttribute("otdel",otdel);
				}else{
					ChildOtdel childOtdel = crudService.getChildOtdel(Long.parseLong(department_id));
					model.addAttribute("childOtdel",childOtdel);
				}
				model.addAttribute("client",client);

			}else{
				client.setFio(fio);
				client.setPhone(phone);
				client.setSumma_dolga(Float.parseFloat(balance));
				crudService.save(client);
				Otdel otdel = crudService.getOtdel(Long.parseLong(department_id));
				if(otdel!=null){
					model.addAttribute("otdel",otdel);
				}else{
					ChildOtdel childOtdel = crudService.getChildOtdel(Long.parseLong(department_id));
					model.addAttribute("childOtdel",childOtdel);
				}
				model.addAttribute("client", client);
			}
		}
		return "new_client";
	}


	@RequestMapping(value = "client_info",method = RequestMethod.GET)
	public String client_info(@RequestParam("client_id")String client_id,ModelMap model) {
		if(client_id!=null){
			if(!client_id.isEmpty()&&!client_id.equals("0")){
				Client client = crudService.getClient(Long.parseLong(client_id));
				Otdel otdel = crudService.getOtdel(client.getOtdel_id());
				if(otdel==null){
					ChildOtdel childOtdel = crudService.getChildOtdel(client.getOtdel_id());
					model.addAttribute("childOtdel",childOtdel);
					model.addAttribute("client",client);
				}else{
					model.addAttribute("otdel",otdel);
					model.addAttribute("client",client);
				}
			}
		}

		return "new_client";
	}





	@RequestMapping(value = "clients",method = RequestMethod.GET)
	public String childOtdelClients(@RequestParam("child_department_id")String child_department_id,ModelMap model) {
		if(child_department_id!=null){
			if(!child_department_id.isEmpty()&&!child_department_id.equals("0")){
				List<Client> clients = crudService.getClients(Long.parseLong(child_department_id));
				if(clients!=null){
					if(clients.size()!=0){
						model.addAttribute("clients",clients);
					}
				}
			}
		}

		return "clients_child_department";
	}



	@RequestMapping(value = "all_clients",method = RequestMethod.GET)
	public String all_clients(ModelMap model) {
		List<Client> list = crudService.getClients();
		if(list!=null){
			if(list.size()!=0){
				model.addAttribute("clients",list);
			}
		}
		return "clients_child_department";
	}

	@RequestMapping(value = "balance",method = RequestMethod.GET)
	public String balance(ModelMap model) {
		Balance balance = crudService.getBalance();
		if(balance!=null){
			model.addAttribute("balance",balance.getBalance_sum());
		}
		return "balance";
	}

	@RequestMapping(value = "save_balance",method = RequestMethod.POST)
	public String save_balance(ModelMap model,HttpServletRequest httpServletRequest) {
		String balance = httpServletRequest.getParameter("balance");
		if(balance!=null){

			if(!balance.equals("0")&&!balance.isEmpty()){
				crudService.updateBalance(Float.parseFloat(balance));
			}else{
				Balance balance1 = new Balance();
				balance1.setBalance_sum(Float.parseFloat(balance));
				crudService.save(balance1);
			}
		}
		Balance balance1 = crudService.getBalance();
		if(balance1!=null){
			model.addAttribute("balance",balance1.getBalance_sum());
		}
		return "balance";
	}


	@RequestMapping(value = "all_orders",method = RequestMethod.GET)
	public String order(ModelMap model) {
		List<Order> orders = crudService.getOrders();
		if(orders!=null){
			if(orders.size()!=0){
				model.addAttribute("orders",orders);
			}
		}
		return "orders";
	}

	@RequestMapping(value = "order_info",method = RequestMethod.GET)
	public String order_info(ModelMap model,@RequestParam("order_id") String order_id) {
		Order order = crudService.getOrder(Long.parseLong(order_id));
		if(order!=null){
			model.addAttribute("order",order);
		}
		return "order_info";
	}

	@RequestMapping(value = "new_order",method = RequestMethod.GET)
	public String new_order(ModelMap model) {
		return "new_order";
	}




	@RequestMapping(value = "save_order",method = RequestMethod.GET)
	public String save_order(ModelMap model,HttpServletRequest httpServletRequest) {
		Order order = new Order();

		long cl_id = Long.parseLong(httpServletRequest.getParameter("cl_id"));
		String cl_fio = httpServletRequest.getParameter("cl_fio");

		order.setCl_fio(cl_fio);
		order.setCl_id(cl_id);




		//String inp_date = httpServletRequest.getParameter("cl_fio");
		//String full_amount = httpServletRequest.getParameter("cl_fio");
		//List<Product> products = httpServletRequest.getParameter("cl_fio");




		List<Order> orders = crudService.getOrders();
		if(orders!=null){
			if(orders.size()!=0){
				model.addAttribute("orders",orders);
			}
		}
		return "orders";
	}


}


































