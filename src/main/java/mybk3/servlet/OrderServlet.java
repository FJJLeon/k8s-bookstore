package mybk3.servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mybk3.dao.BookDao;
import mybk3.dao.OrderDao;
import mybk3.dao.OrderitemDao;
import mybk3.dao.UserDao;
import mybk3.model.Books;
import mybk3.model.Order;
import mybk3.model.Orderitem;
import mybk3.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * Servlet implementation class OrderServlet
 */
@Component
@RestController
@Transactional
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/MybkServlet/submitOrder", method = RequestMethod.POST)
	@Transactional
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();

			String currUserName = request.getParameter("Cname");
			System.out.println("user:"+currUserName);
			String jsonData = request.getParameter("cart");
			JSONArray jsonLine = JSONArray.fromObject(jsonData);
			
			Order order = new Order();
			/* order has foreign key userid,  one user to many order 
			 * but userid is not explicit stated in User while stated in @joinColumn then you got a cloumn called userid in the table called order
			 * besides, the entity user should have a set stored all mapped orders entity which won't appear in table
			 * the same for order and orderitem's one to many relation
			 */
			List<User> users = em.createNamedQuery("User.findByName",User.class).setParameter("name", currUserName).getResultList();
			User currUser = users.get(0);
			System.out.print("curr User" + currUser.getUsername() + currUser.getPassword());
			
			order.setUser(currUser);
			
			Timestamp date=new Timestamp(new java.util.Date().getTime());
			order.setDate(date);
			
			int totcost = 0;
			for (int i = 0; i<jsonLine.size(); i++) {
				JSONArray jsonbook = (JSONArray) jsonLine.get(i);
				
				Orderitem orderitem = new Orderitem();
				
				int amount;
				if ((amount = (Integer)jsonbook.get(5)) == 0 )
					continue;				
				orderitem.setAmount(amount);
				
				List<Books> books = em.createNamedQuery("Books.findByTitle",Books.class).setParameter("title", (String)jsonbook.get(0)).getResultList();
				orderitem.setBookid(books.get(0).getId());
				totcost += books.get(0).getPrice() * amount;
				order.addOrderItem(orderitem);
			}
			order.setTotcost(totcost);
			
			currUser.addOrderorders(order);
			em.merge(currUser);
			//tx.commit();
			//session.close();

			out.println("haha");
			out.flush();
			out.close();
		} catch (Exception ex) {
			if (ServletException.class.isInstance(ex)) {
				throw (ServletException) ex;
			} else {
				throw new ServletException(ex);
			}
		}
	}
}