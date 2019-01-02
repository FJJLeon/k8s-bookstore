package mybk3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mybk3.model.Order;

public interface OrderDao extends JpaRepository<Order, Long>{

	//public Integer save(Order order);

	//public void delete(Book book);

	//public void update(Book book);

	//public Book getBookById(int id);

	//public List<Book> getAllBooks();
	
	List<Order> findById(int id); 
}