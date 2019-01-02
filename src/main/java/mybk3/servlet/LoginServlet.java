package mybk3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

import mybk3.dao.BookDao;
import mybk3.dao.UserDao;
import mybk3.model.User;

@Component
@RestController
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserDao userdao;
	@PersistenceContext
	protected EntityManager em; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value = "/MybkServlet/checkuser", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();

			String username = request.getParameter("name");
			String password = request.getParameter("pwd");
			Boolean isValid = login(username, password);

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
	private Boolean login(String username, String password) {
		Boolean isValid = false;
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<User> result = (List<User>) session
				.createQuery("select user from User user where username = :name and password = :pwd")
				.setParameter("name", username).setParameter("pwd", password).list();
		tx.commit();
		
		if (result.size() > 0) {
			isValid = true;
			System.out.println(result);
		}
		else
			isValid = false;
		session.close();*/
		System.out.println(username + password);
		//List<User> result = em.createNamedQuery("User.queryLogin", User.class).setParameter("name", username).setParameter("pwd", password).getResultList();
		List<User> result = userdao.loginQuery(username, password);
		if (result.size() > 0) {
			isValid = true;
			System.out.println(result);
		}
		else
			isValid = false;

		return isValid;
	}

}