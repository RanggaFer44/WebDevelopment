/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.LibDAO;
import com.model.Book;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author HELLO
 */

@ManagedBean(name="update")
@SessionScoped

public class Update {
    
    Book UBook = new Book();
    LibDAO ldao = new LibDAO();
    private List <Book> bookList;

    public Book getUBook() {
        return UBook;
    }

    public void setUBook(Book UBook) {
        this.UBook = UBook;
    }

    public LibDAO getLdao() {
        return ldao;
    }

    public void setLdao(LibDAO ldao) {
        this.ldao = ldao;
    }
    
    
    
    
    
    public void UpdateBook(Book book)
    {
        String Title = book.getTitle();
        FacesMessage message1 = new
        FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        ldao.update(book);
        System.out.println("Book Info successfully saved.");
        FacesMessage message = new
        FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Book updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        book = new Book();
    }
    
    public void changeBook(Book UBook)
{
this.UBook = UBook;
}
    
    public void onRowEdit(RowEditEvent event)
{
FacesMessage msg = new FacesMessage(" Edited Record No",
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
    
}
