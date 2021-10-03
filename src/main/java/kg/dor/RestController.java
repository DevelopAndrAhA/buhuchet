package kg.dor;

import kg.dor.models.Client;
import kg.dor.models.Courier;
import kg.dor.models.Order;
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
			List<Client> clients = new ArrayList<>();
			if(l!=null && l.size()!=0){
				model.addAttribute("orders",l);
				for(int i = 0;i<l.size();i++){
					clients.add(crudService.getClient(l.get(i).getCl_id()));
				}
				model.addAttribute("clients",clients);
				httpSession.setAttribute("orderSize", l.size());
				httpSession.setAttribute("clickedOrder",false);
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




}