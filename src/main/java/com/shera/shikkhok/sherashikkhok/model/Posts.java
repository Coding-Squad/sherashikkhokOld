package com.shera.shikkhok.sherashikkhok.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "posts")
public class Posts {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_Id")
    private Long id;

    @Column(name= "post_title" ,nullable = false)
	@NotEmpty(message = "*Please provide a title")
    private String title;

    @Column(name = "post_body")
	@NotEmpty(message = "*Please provide contents")
    private String body;
    
   /* @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_posts", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private Set<User> users;*/
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User author;
    
   
    public User getAuthor() {
		return author;
	}
    
	public void setAuthor(User author) {
		this.author = author;
	}

	@Column(nullable = false)
    private Date date = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	

	public Posts() {}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", title=" + title + ", body=" + body + ", author=" + author + ", date=" + date
				+ "]";
	}

	public Posts(Long id, String title, String body, User author, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
	}
	
	
}
