package kg.dor.service;

import kg.dor.models.*;
import kg.dor.models.Order;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by altynbek.kochkonbaev on 21.09.2021.
 */
@Repository
@Transactional
public class CrudService {



    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;

    /*=======================================================================================================================================================================================*/
        public boolean authorization(String login,String password,HttpServletRequest httpServletRequest){
            Criteria criteria = session.getCurrentSession().createCriteria(Admin.class);
            criteria.add(Restrictions.eq("login",login));
            criteria.add(Restrictions.eq("password",password));
            Admin admin = (Admin) criteria.uniqueResult();
            HttpSession httpSession = httpServletRequest.getSession();
            if(admin!=null){
                httpSession.setAttribute("admin",admin);
                return true;
            }else{
                Criteria criteria2 = session.getCurrentSession().createCriteria(Courier.class);
                criteria2.add(Restrictions.eq("login",login));
                criteria2.add(Restrictions.eq("password",password));
                Courier courier = (Courier) criteria2.uniqueResult();
                if(courier!=null){
                    httpSession.setAttribute("courier",courier);
                    return true;
                }
            }
            return false;
        }
    /*=======================================================================================================================================================================================*/
        public void save(Client client){
            session.getCurrentSession().save(client);
        }

        public void save(Courier courier){
            session.getCurrentSession().save(courier);
        }
        public void save(ChildOtdel childOtdel){
        session.getCurrentSession().save(childOtdel);
    }

        public void save(Order order){
            session.getCurrentSession().save(order);
        }

        public void save(Otdel otdel){
            session.getCurrentSession().save(otdel);
        }

        public void save(Balance balance){
        session.getCurrentSession().save(balance);
    }

        public void save(Purchase purchase){
            session.getCurrentSession().save(purchase);
        }
    /*=======================================================================================================================================================================================*/

        public void update(Courier courier){
            session.getCurrentSession().update(courier);
        }


        public float update(Client client){
            Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
            Client client1  = (Client)criteria.add(Restrictions.eq("cl_id",client.getCl_id())).uniqueResult();
            float summaDolga  = client1.getSumma_dolga();


            if(client.getSumma_dolga()==0){
                client.setSumma_dolga(0);
                client1.setSumma_dolga(0);
            }else if(summaDolga>client.getSumma_dolga()){
                summaDolga = client.getSumma_dolga() - summaDolga;
                summaDolga = summaDolga*1;
                client1.setSumma_dolga(0);
            }

            client1.setSumma_dolga(client.getSumma_dolga());
            client1.setOtdel_id(client.getOtdel_id());
            client1.setPhone(client.getPhone());
            client1.setFio(client.getFio());
            client1.setCl_id(client.getCl_id());

            session.getCurrentSession().update(client1);


            return summaDolga;
        }
        public void update(Otdel otdel){
        session.getCurrentSession().update(otdel);
    }
        public void update(ChildOtdel childOtdel){
        session.getCurrentSession().update(childOtdel);
    }
        public void updateBalance(float balance){
            Criteria criteria = session.getCurrentSession().createCriteria(Balance.class);
            criteria.setMaxResults(1);
            Balance balance1 = (Balance)criteria.uniqueResult();
            balance1.setBalance_sum(balance);
            session.getCurrentSession().update(balance1);
        }
        public void updateBalance(float balance,String type){

            float curr_balance = 0;

            Criteria criteria = session.getCurrentSession().createCriteria(Balance.class);
            criteria.setMaxResults(1);
            Balance balance1 = (Balance)criteria.uniqueResult();

            curr_balance = balance1.getBalance_sum();

            if(type.equals("plu")){
                balance1.setBalance_sum(curr_balance+balance);
            }else if(type.equals("min")){
                balance1.setBalance_sum(curr_balance-balance);
            }

            session.getCurrentSession().update(balance1);
        }
        public void clientAmountUpdate(float amount,long client_id){
            Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
            criteria.add(Restrictions.eq("cl_id",client_id));
            Client client = (Client)criteria.uniqueResult();
            float summaDolga = client.getSumma_dolga();
            if(amount!=0){
                summaDolga = summaDolga - amount;
            }else{
                summaDolga = 0;
            }

            client.setSumma_dolga(summaDolga);
            session.getCurrentSession().update(client);
        }

        public void updateOrder(long order_id,float summa,Date return_date){
            String status = "N";

            Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
            criteria.add(Restrictions.eq("order_id",order_id));
            Order order = (Order) criteria.uniqueResult();


            if(order.getFull_amount()==summa && summa>0){
                status="Y";
                order.setFull_amount(summa);
            }else if(order.getFull_amount()>summa && summa>0){
                order.setFull_amount(order.getFull_amount()-summa);
            }else if(summa==0){
                status="Y";
                order.setFull_amount(0);
            }
            order.setStatus(status);
            order.setReturn_cos_date(return_date);
            session.getCurrentSession().update(order);
        }


    /*=======================================================================================================================================================================================*/





                                                                             /*DELETE*/





    /*=======================================================================================================================================================================================*/

    public List getClients(){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        criteria.addOrder(org.hibernate.criterion.Order.desc("cl_id"));
        List list  = criteria.list();
        return list;
    }
    public Balance getBalance(){
        Criteria criteria = session.getCurrentSession().createCriteria(Balance.class);
        criteria.setMaxResults(1);
        Balance balance = (Balance) criteria.uniqueResult();
        return balance;
    }
    public List getClients(long [] clients_id){
       String sql = "select * from DOR_CLIENT t where t.cl_id in (";
               for(int i=0;i<clients_id.length;i++){
                   if(i<clients_id.length-1){
                       sql = sql+clients_id[i]+",";
                   }else{
                       sql = sql+clients_id[i];
                   }
               }
               sql = sql +")";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql).addEntity(Client.class);
        List list = sqlQuery.list();
        if(list!=null){
            return list;
        }
        return null;
    }
    public List getCouriers(){
        Criteria criteria = session.getCurrentSession().createCriteria(Courier.class);
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List search_client(String fio){
        String sql = "select * from DOR_CLIENT t where upper(t.fio) like '%"+fio.toUpperCase()+"%'";
        //Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql).addEntity(Client.class);
        //criteria.add(Restrictions.like("fio", fio, MatchMode.START));
        //List l = criteria.list();
        List l = sqlQuery.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getOrders(){
        Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("status","N"));
        criteria.setMaxResults(500);
        criteria.addOrder(org.hibernate.criterion.Order.desc("order_id"));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getOrders(Date return_cos_date){
        Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("return_cos_date",return_cos_date));
        criteria.add(Restrictions.eq("status","N"));
        criteria.addOrder(org.hibernate.criterion.Order.desc("order_id"));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getPurchaseList(Date startDate,Date end_date){
//        Criteria criteria = session.getCurrentSession().createCriteria(Purchase.class);
//        criteria.add(Restrictions.ge("inp_date", startDate));
//        criteria.add(Restrictions.lt("inp_date", end_date));
//
//        criteria.addOrder(org.hibernate.criterion.Order.desc("p_id"));
        String sql = "select * from DOR_Purchase t where t.inp_date between '"+startDate+"' and '"+end_date+"' order by p_id desc";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql).addEntity(Purchase.class);
        //List l = criteria.list();
        List l = sqlQuery.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getOrders(Date startDate,Date end_date){
        /*Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.ge("inp_date", startDate));
        criteria.add(Restrictions.lt("inp_date", end_date));

        criteria.addOrder(org.hibernate.criterion.Order.desc("order_id"));*/
        String sql = "select * from DOR_ORDER t where t.inp_date BETWEEN '"+startDate+"'"+" and '"+end_date+"' order by t.order_id desc";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql).addEntity(Order.class);
        //List l = criteria.list();
        List l = sqlQuery.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getPurchaseProducts(long purchase_id){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select * from DOR_PURCHProduct t where t.purchase_id="+purchase_id);
        sqlQuery.addEntity(PProduct.class);
        List l = sqlQuery.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public Order getOrder(long order_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("order_id",order_id));
        Order order = (Order) criteria.uniqueResult();
        if(order!=null){
            return order;
        }
        return null;
    }
    public List getOtdels(){
        Criteria criteria = session.getCurrentSession().createCriteria(Otdel.class);
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }

    public List getChildOtdels(long otdel_id){
        Criteria criteria = session.getCurrentSession().createCriteria(ChildOtdel.class);
        criteria.add(Restrictions.eq("parent_otdel",otdel_id));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getClients(long otdel_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("otdel_id",otdel_id));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }

    public List getProducts(long order_id){
       SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select * from DOR_PRODUCT t where t.order_id = "+order_id).addEntity(Product.class);
        List list = sqlQuery.list();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public float getClientDolg(long cl_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        Client client  = (Client)criteria.add(Restrictions.eq("cl_id",cl_id)).uniqueResult();
        return client.getSumma_dolga();

    }

    /*=======================================================================================================================================================================================*/

    public Client getClient(long client_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("cl_id",client_id));
        Client client =(Client) criteria.uniqueResult();
        if(client!=null){
            return client;
        }
        return null;
    }
    public Courier getCourier(long client_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Courier.class);
        criteria.add(Restrictions.eq("cr_id",client_id));
        Courier courier =(Courier) criteria.uniqueResult();
        if(courier!=null){
            return courier;
        }
        return null;
    }
    public Otdel getOtdel(long otdel_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Otdel.class);
        criteria.add(Restrictions.eq("otdel_id",otdel_id));
        Otdel otdel =(Otdel) criteria.uniqueResult();
        if(otdel!=null){
            return otdel;
        }
        return null;
    }
    public ChildOtdel getChildOtdel(long otdel_id){
        Criteria criteria = session.getCurrentSession().createCriteria(ChildOtdel.class);
        criteria.add(Restrictions.eq("child_otdel_id",otdel_id));
        ChildOtdel childOtdel =(ChildOtdel) criteria.uniqueResult();
        if(childOtdel!=null){
            return childOtdel;
        }
        return null;
    }






    /*=======================================================================================================================================================================================*/


    public void save(LoginPassword loginPassword){
        session.getCurrentSession().save(loginPassword);
    }


}
