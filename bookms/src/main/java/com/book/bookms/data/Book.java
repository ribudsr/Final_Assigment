package com.book.bookms.data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	//title, publishedDate,  totalCopies, issuedCopies, author
	@Id
	@Column(name="ISBN")
	private Long isbn;
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Long getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(Long totalCopies) {
		this.totalCopies = totalCopies;
	}
	public Long getIssuedCopies() {
		return issuedCopies;
	}
	public void setIssuedCopies(Long issuedCopies) {
		this.issuedCopies = issuedCopies;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="TITLE")
	private String title;
	@Column(name="PUBLISHEDDATE")
	private Date publishedDate;
	@Column(name="TOTALCOPIES")
	private Long totalCopies;
	@Column(name="ISSUEDCOPIES")
	private Long issuedCopies;
	@Column(name="AUTHOR")
	private String author;
	
	
	
}
