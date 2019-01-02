package mybk3.servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.javafx.collections.MappingChange.Map;

import mybk3.dao.BookDao;
import mybk3.dao.UserDao;
import mybk3.model.Books;
import net.sf.json.JSONArray;


/**
 * Servlet implementation class UserManagerServlet
 */
@Component
@RestController
public class BookManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	BookDao bookdao;
	@PersistenceContext
	protected EntityManager em; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookManagerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/MybkServlet/BookManager", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            /*HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("This is a book manager");

            List<Book> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Book").list(); 
            Iterator<Book> it = result.iterator();
            */
			System.out.println("This is a book manager");
			
			List<Books> result = bookdao.getAllBooks(); //!!!
			Iterator<Books> it = result.iterator();
			ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
            while (it.hasNext()) {
                Books book = (Books) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(book.getTitle());
                arrayList.add(book.getAuthor());
                arrayList.add(book.getLanguage());
                arrayList.add(book.getPublished());
                arrayList.add(Integer.toString((int) book.getPrice())); //!!!
                arrayList.add(book.getSales());                             
                booksJson.add(JSONArray.fromObject(arrayList));
            }
            JSONArray books = JSONArray.fromObject(booksJson.toArray());
            
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");


            System.out.println(books);

            out.println(books);
            out.flush();
            out.close();
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        }
        catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw ( ServletException ) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
	}
}
