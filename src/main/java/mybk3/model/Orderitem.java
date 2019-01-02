package mybk3.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderitems")
public class Orderitem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private int bookid;
	private int amount;

	public Orderitem() {
	}

	public Orderitem( int bookid, int amount) {
		this.bookid = bookid;
		this.amount = amount;
	}

	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)  
    @JoinColumn(name="orderid") //指定外键的名称。外键一般都是在关系维护端定义  
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
*/
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
