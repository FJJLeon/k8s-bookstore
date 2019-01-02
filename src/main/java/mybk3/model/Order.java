package mybk3.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id  
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
	private int id;
	private int totcost;
	private Timestamp date;

	public Order() {
	}

	public Order( Timestamp date) {
		this.date = date;
	}

	@OneToMany(cascade={CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy="order")  
	private Set<Orderitem> orderitems = new HashSet<Orderitem>();

	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}
	
	public void addOrderItem(Orderitem orderItem){  
        orderItem.setOrder(this); //因为OrderItem是关系维护端  
        this.orderitems.add(orderItem);  
    }
	
	//映射多对一的关联关系
    @JoinColumn(name="USERID", nullable=false)//关联user表的字段
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
*/
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getTotcost() {
		return totcost;
	}

	public void setTotcost(int totcost) {
		this.totcost = totcost;
	}
}
