package mybk3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mybk3.model.Books;
import mybk3.model.Order;
import mybk3.model.Orderitem;
import mybk3.model.User;

/**
 * Servlet implementation class QueryUser
 */
@Component
@RestController
public class ManageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUser() {
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value = "/MybkServlet/queryUser", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();

			String username = request.getParameter("username");
			System.out.printf("name:%s\n",username);
			String res = queryUser(username);

			out.println(res);
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
	
	@SuppressWarnings("unchecked")
	private String queryUser(String username) {
		String res = "haha";
		
		List<User> users = em.createNamedQuery("User.findByName",User.class).setParameter("name", username).getResultList();
		User currUser = users.get(0);
		System.out.println("user "+currUser.getUsername() + " id " + currUser.getId());
		
		List<Order> tmporder = em.createQuery("select o from Order o where userid = :userid")
								.setParameter("userid", currUser.getId())
								.getResultList();
		int booknum = 0;
		int allcost = 0;
		for (Order tmpo : tmporder) {
			allcost += tmpo.getTotcost();
			Set<Orderitem> tmpset = tmpo.getOrderitems();
			for (Orderitem tmpoi : tmpset) {
				booknum += tmpoi.getAmount();
			}
		}
		
		/*
		List<Order> allcost = 
					 em.createNativeQuery("select * from orders where userid = :userid")
		 .setParameter("userid", currUser.getId()).getResultList();
		
		Iterator<Order> xx = allcost.iterator();
		while (xx.hasNext()) {
			Order tmp = xx.next();
			System.out.println("haha "+tmp.getTotcost());
		}
		
		System.out.print("booknum "+booknum + " allcost " + allcost );
		*/
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		int result = Integer.parseInt( 
				session
				.createQuery("select sum(amount) from Order where username = :name group by username")
				.setParameter("name", username)
				.uniqueResult().toString()
				);
		*/
		res = "The user "+ username + " has bought " + booknum + " books and total cost reach " + allcost;
		
		//tx.commit();
		//session.close();
		return res;
	}
	
}