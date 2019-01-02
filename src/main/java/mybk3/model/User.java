package mybk3.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name = "User.queryLogin",
				query = "select u from User u where u.username = :name and u.password = :pwd "),
	@NamedQuery(name = "User.findByName",
				query = "select u from User u where u.username = :name"),
})
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String role;

	public User() {
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@OneToMany(cascade={CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="user")  
	private Set<Order> userorders = new HashSet<Order>();
	
	public Set<Order> getUserorders() {
		return userorders;
	}

	public void setUserorders(Set<Order> userorders) {
		this.userorders = userorders;
	}
	
	public void addOrderorders(Order order){  
		order.setUser(this); 
        this.userorders.add(order);  
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
