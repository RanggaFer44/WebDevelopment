/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Util.HibernateUtil;
import com.model.Login;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HELLO
 */
@ManagedBean
@SessionScoped
public class Log {
    
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();
    Login login = new Login();

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    public String valLog () {
    
        session.getTransaction();
        
        List <Log> list = session.createSQLQuery("select * from login where userName='"+login.getUserName()+"' and passWord='"+login.getPassWord()+"'").list();
           
        if (list.size()>0) {
            return "mainMenu";
        }else
        {
        
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "wrong username or password"," "));
        
        }
        return null;
    }
    
    public String loggedUser(){
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if(session != null){
            
            return login.getUserName();
        
        }
        return null;
    }
    
}
