package mybk3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mybk3.model.Orderitem;

public interface OrderitemDao extends JpaRepository<Orderitem, Long>{

	/*public Integer save(Book book);

	public void delete(Book book);

	public void update(Book book);

	public Book getBookById(int id);*/

	//public List<Book> getAllBooks();
	
	List<Orderitem> findById(int id); 
}