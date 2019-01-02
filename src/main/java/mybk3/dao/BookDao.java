package mybk3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mybk3.model.Books;

public interface BookDao extends JpaRepository<Books, Long>{

	/*public Integer save(Book book);

	public void delete(Book book);

	public void update(Book book);

	public Book getBookById(int id);*/

	@Modifying
	@Query("select b from Books b")
	public List<Books> getAllBooks();
	
	List<Books> findByTitle(String title); 
}