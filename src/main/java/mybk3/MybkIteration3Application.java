package mybk3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mybk3.dao.BookDao;
import mybk3.model.Books;

@RestController
@SpringBootApplication
public class MybkIteration3Application extends SpringBootServletInitializer{

	
	@Autowired  
	BookDao bookdao;
	
	@GetMapping("/cao")
    public List<Books> say2(){
    	List<Books> res = bookdao.findAll();
        return res;
    }
	
	@GetMapping("/hello")
    public String say(){
        return "Hello World";
    }
	public static void main(String[] args) {
		SpringApplication.run(MybkIteration3Application.class, args);
	}
}
