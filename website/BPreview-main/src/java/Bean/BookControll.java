/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.LibDAO;
import Util.HibernateUtil;
import com.model.Book;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Convert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author HELLO
 */
@ManagedBean(name = "bookC")
@ViewScoped
public class BookControll {
    
    private List <Book> searchByID;
    private List < Book > bookList;
    private List < Book > bookUL;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private List <Book> viewBooks;
    Book book = new Book();
    Book UBook = new Book();
    Book newbook = new Book();
    Book SBook = new Book();
    Book DelBook = new Book();
    LibDAO ldao = new LibDAO();
    
    public List < Book > getBooks()
    {   
        viewBooks = ldao.AllBooks();
        int count = viewBooks.size();
        return viewBooks;
    }
    
    
    public String UpdateBook(Book book)
    {
        Session session = sessionFactory.openSession();
        session.getTransaction();
        viewBooks = session.createSQLQuery("select * from book where ID = '"+book.getId()+"'").list();
        String Title = book.getTitle();
        FacesMessage message1 = new
        FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        if (viewBooks.size()>0) {
            ldao.update(book);
            System.out.println("Book Info successfully saved.");
            FacesMessage message = new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book updated successfully .");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else {
            FacesMessage m = new
            FacesMessage(FacesMessage.SEVERITY_WARN, "Save Information", "ID not found");
            RequestContext.getCurrentInstance().showMessageInDialog(m);
        }
        
        book = new Book();
        return null;
    }
    
    public void changeBook(Book UBook)
{
this.UBook = UBook;
}
    
    public void onRowEdit(RowEditEvent event)
{
FacesMessage msg = new FacesMessage(" Edited Record on",
((Book) event.getObject()).getTitle());
FacesContext.getCurrentInstance().addMessage(null, msg);
Book editedbook = (Book) event.getObject();
ldao.update(editedbook);
}
public void onCancel(RowEditEvent event)
{
FacesMessage msg = new FacesMessage("Edit Cancelled");
FacesContext.getCurrentInstance().addMessage(null, msg);
bookList.remove((Book) event.getObject());
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
    
    
    
public void searchbyBookId()
{
searchByID = ldao.SearchByBookId(Integer.toString(book.getId()));
int count = searchByID.size();
FacesMessage message = new
FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));
RequestContext.getCurrentInstance().showMessageInDialog(message);
}

    public List<Book> getBookUL() {
        return bookUL;
    }

    public void setBookUL(List<Book> bookUL) {
        this.bookUL = bookUL;
    }

    public Book getSBook() {
        return SBook;
    }

    public void setSBook(Book SBook) {
        this.SBook = SBook;
    }

    
    
    public List<Book> getSearchByID() {
        return searchByID;
    }

    public void setSearchByID(List<Book> searchByID) {
        this.searchByID = searchByID;
    }

    public Book getUBook() {
        return UBook;
    }

    public void setUBook(Book UBook) {
        this.UBook = UBook;
    }

    public Book getNewbook() {
        return newbook;
    }

    public void setNewbook(Book newbook) {
        this.newbook = newbook;
    }

    public Book getDelBook() {
        return DelBook;
    }

    public void setDelBook(Book DelBook) {
        this.DelBook = DelBook;
    }

    public List<Book> getViewBooks() {
        return viewBooks;
    }

    public void setViewBooks(List<Book> viewBooks) {
        this.viewBooks = viewBooks;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibDAO getLdao() {
        return ldao;
    }

    public void setLdao(LibDAO ldao) {
        this.ldao = ldao;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
//    public void onRowEdit(RowEditEvent event)
//{
//FacesMessage msg = new FacesMessage(" Edited Record No",
//((Book) event.getObject()).getId());
//FacesContext.getCurrentInstance().addMessage(null, msg);
//Book editedbook = (Book) event.getObject();
//ldao.update(editedbook);
//}
    

    

public void addBook()
{
Integer userId = 0;
userId = ldao.getId();
newbook.setId(userId);
Integer Id = newbook.getId();
newbook.setId(Id);
newbook.setTitle(book.getTitle());
newbook.setPreview(book.getPreview());
newbook.setWriter(book.getWriter());
newbook.setLink(book.getLink());
newbook.setReleaseDate(book.getReleaseDate());
ldao.add(newbook);
System.out.println("Book successfully saved.");
FacesMessage message = new
FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book successfully saved.");
RequestContext.getCurrentInstance().showMessageInDialog(message);
newbook = new Book();
}


}

