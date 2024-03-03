/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Util.HibernateUtil;
import com.model.Book;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HELLO
 */
@ManagedBean
@SessionScoped
public class Delete {

    /**
     * Creates a new instance of Delete
     */
        Book DelBook = new Book();

    public Book getDelBook() {
        return DelBook;
    }

    public void setDelBook(Book DelBook) {
        this.DelBook = DelBook;
    }
        
        
        
        public void DelBook (Book book){
            
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            
            try
            {
//                String name = book.getTitle();
                session.beginTransaction();
                session.delete(book);
                session.flush();
                session.getTransaction().commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
                session.close();
            
        }
    
    
    
}
