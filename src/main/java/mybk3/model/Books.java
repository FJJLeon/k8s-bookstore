package mybk3.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="books")
@NamedQueries({
	@NamedQuery(name = "Books.findByTitle",
				query = "select b from Books b where b.title = :title"),
})
public class Books implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String author;
	private String language;
	private double price;
	private String published;
	private String sales;
	
	public Books() {
	}

	public Books(String title, String author, double price, String published) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.published = published;
		//this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}
/*
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
*/

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}