package com.apigateway.apigateway.data;


public class IssueDO {

	private Long ID;
	
	private Long isbn;
	
	
	private Long custId;
	
	
	private Long noOfCopies;

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Long noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
	
	
	
}
