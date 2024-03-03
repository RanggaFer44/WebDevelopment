/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.LibDAO;
import Util.HibernateUtil;
import com.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author HELLO
 */
@ManagedBean
@SessionScoped
public class Search {
        
        private List <Book> searchByID;
        Book book = new Book();
        LibDAO ldao = new LibDAO();
        

        public void searchbyBookId()
{
searchByID = ldao.SearchByBookId(Integer.toString(book.getId()));
int count = searchByID.size();
FacesMessage message = new
FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));
RequestContext.getCurrentInstance().showMessageInDialog(message);
}
    
}
