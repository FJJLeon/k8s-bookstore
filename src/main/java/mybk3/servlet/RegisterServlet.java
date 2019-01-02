package mybk3.servlet;

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
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mybk3.dao.UserDao;
import mybk3.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@Component
@RestController
@Transactional
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value = "/MybkServlet/register", method = RequestMethod.POST)
	@Transactional
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();

			String username = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String password = request.getParameter("pwd");
			
			System.out.printf("name:%s pwd:%s phone:%s email:%s\n",username,password, phone, email);
			Boolean isValid = register(username, password, phone, email);
			
			out.println(isValid);
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
	@Transactional
	private Boolean register(String username, String password, String phone, String email) {
		Boolean isValid = false;
		
		List<User> result = em.createNamedQuery("User.findByName",User.class).setParameter("name", username).getResultList();
		if (result.size() > 0){
			return false;
		}
		
		User newuser = new User();
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setPhone(phone);
		newuser.setEmail(email);
		newuser.setRole("USER");
		
		em.persist(newuser);
		isValid = true;
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User newuser = new User();
		newuser.setId((long)88); // tmp rand set 
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setPhone(phone);
		newuser.setEmail(email);
		newuser.setRole("USER");
		
		session.save(newuser);
		tx.commit();
		
		isValid = true;
		session.close();
		*/
		
		return isValid;
	}

}
