package mybk3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mybk3.model.User;

public interface UserDao extends JpaRepository<User, Long>{

	/*public Integer save(Book book);

	public void delete(Book book);

	public void update(Book book);

	public Book getBookById(int id);*/

	//public List<Book> getAllBooks();
	
	//List<Orderitem> findByTitle(String title); 
	
	@Modifying
	@Query("select u from User u where u.username = ?1 and u.password = ?2 ")
	public List<User> loginQuery(String name, String pwd );
	
}