/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.HibernateUtil;
import com.model.Book;
import com.model.Login;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author HELLO
 */
public class LibDAO {
    
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
	public boolean create(Login login) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(login);
			transaction.commit();
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return result;
	}
        
        private List <Book> allBook;
        
        public List < Book > AllBooks()
        {
            SessionFactory factory = HibernateUtil.getSessionFactory();
                    Session session = factory.openSession();
            try
            {
                session.beginTransaction();
                allBook = session.createCriteria(Book.class).list();
                int count = allBook.size();
                FacesMessage message1 = new
                FacesMessage(FacesMessage.SEVERITY_INFO, "List Size",
                Integer.toString(count));//Debugging Purpose
                //RequestContext.getCurrentInstance().showMessageInDialog(message1);
                session.getTransaction().commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
                session.close();
                return allBook;
        }
        
        
        
        private List <Book> list;
        
        public List < Book > singleBook()
        {
            SessionFactory factory = HibernateUtil.getSessionFactory();
                    Session session = factory.openSession();
            try
            {
                session.beginTransaction();
                list = session.createCriteria(Book.class).list();
                
                list.set(, null)
                
                int count = allBook.size();
                FacesMessage message1 = new
                FacesMessage(FacesMessage.SEVERITY_INFO, "List Size",
                Integer.toString(count));//Debugging Purpose
                //RequestContext.getCurrentInstance().showMessageInDialog(message1);
                session.getTransaction().commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
                session.close();
                return allBook;
        }
        
        
        
        
         public void DeleteBook (Book book){
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            
            try
            {
//                String name = book.getTitle();
                session.beginTransaction();
                session.delete(book);
                session.flush();;
                session.getTransaction().commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
                session.close();
            
        }
        
public Integer getId()
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
        String hql = "select max(U.id) from Book U";
        Query query = session.createQuery(hql);
        List < Integer > results = query.list();
        Integer userId = 1;
    if (results.get(0) != null)
    {
        userId = results.get(0) + 1;
    }
    session.flush();
    session.close();
    return userId;
    }
        
        public List < Book > SearchByBookId(String bookId)

    {
    SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
    List < Book > daoSearchList = new ArrayList < > ();
    try
    {
    session.beginTransaction();
    Query qu = session.createQuery("From Book U where U.bookId =:bookId"); //User is the entity not the table
    qu.setParameter("bookId", bookId);
    daoSearchList = qu.list();
    int count = daoSearchList.size();
    session.getTransaction().commit();
    }
    catch (Exception e)
    {
    e.printStackTrace();
    session.getTransaction().rollback();
    }
    finally
    {
    session.close();
    }
    return daoSearchList;
    }
        
        


public void add(Book newbook)
{
Session session =
HibernateUtil.getSessionFactory().openSession();
try
{
String Id = Integer.toString(newbook.getId());
// begin a transaction
session.beginTransaction();
session.merge(newbook);
session.flush();
System.out.println("NewUser saved, id: " +
newbook.getId());
session.getTransaction().commit();
}
catch (Exception e)
{
e.printStackTrace();
session.getTransaction().rollback();
}

session.close();
}
        
        
        public void update(Book book)
{
SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
try
{
session.beginTransaction();
session.update(book);
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
