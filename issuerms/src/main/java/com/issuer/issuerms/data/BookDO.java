package com.issuer.issuerms.data;

import java.sql.Date;

public class BookDO {

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
	
	private String title;
	
	private Date publishedDate;
	
	private Long totalCopies;
	
	private Long issuedCopies;
	
	private String author;
	
	
	
}
