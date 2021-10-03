package kg.dor.service;

import kg.dor.models.*;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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

        public void save(Dolg dolg){
            session.getCurrentSession().save(dolg);
        }

        public void save(Order order){
            session.getCurrentSession().save(order);
        }

        public void save(Otdel otdel){
            session.getCurrentSession().save(otdel);
        }

        public void save(Product product){
            session.getCurrentSession().save(product);
        }
    /*=======================================================================================================================================================================================*/

        public void update(Courier courier){
            session.getCurrentSession().update(courier);
        }






    /*=======================================================================================================================================================================================*/





                                                                             /*DELETE*/





    /*=======================================================================================================================================================================================*/


    public List getClients(){
        Criteria criteria = session.getCurrentSession().createCriteria(Client.class);
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
    public List getOtdels(){
        Criteria criteria = session.getCurrentSession().createCriteria(Otdel.class);
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

}
