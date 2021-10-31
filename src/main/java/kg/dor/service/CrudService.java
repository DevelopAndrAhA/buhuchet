package kg.dor.service;

import kg.dor.models.*;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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

        public void save(Dolg dolg){
            session.getCurrentSession().save(dolg);
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

        public void save(Product product){
            session.getCurrentSession().save(product);
        }
    /*=======================================================================================================================================================================================*/

        public void update(Courier courier){
            session.getCurrentSession().update(courier);
        }
        public void update(Client client){
            session.getCurrentSession().update(client);
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






    /*=======================================================================================================================================================================================*/





                                                                             /*DELETE*/





    /*=======================================================================================================================================================================================*/

    public List getClients(){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
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
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.in("cl_id",clients_id));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
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
    public List getDolgs(){
        Criteria criteria = session.getCurrentSession().createCriteria(Dolg.class);
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            return l;
        }
        return null;
    }
    public List getOrders(){
        Criteria criteria = session.getCurrentSession().createCriteria(Order.class);
        List l = criteria.list();
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
}
