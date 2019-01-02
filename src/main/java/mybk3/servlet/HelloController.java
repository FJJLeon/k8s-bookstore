package mybk3.servlet;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mybk3.dao.BookDao;
import mybk3.model.Books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
 
@Component
@RestController
public class HelloController {
	
    @RequestMapping("/hello2")
    public String say(){
        return "Hello World";
    }
    
}