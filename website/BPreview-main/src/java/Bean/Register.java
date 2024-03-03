/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.LibDAO;
import com.model.Login;
import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author HELLO
 */

@ManagedBean(name = "reg")
@SessionScoped 
public class Register implements Serializable{
    
    LibDAO ldao = new LibDAO();    
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    public Register () {
        this.login = new Login();
    }
    
    public String Registration() {
		ldao.create(this.login);
		return "loginPage?faces-redirect=true";
	}
    
}
