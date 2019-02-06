package com.bakalis.jsf.managedBeans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bakalis.jsf.services.TransactionsService;

@ManagedBean(name = "transactions", eager = true)
@ViewScoped
public class TransactionsBean {
	
	protected TransactionsService transactionsService;
	
	
	//Strings for the Data we need to store the Transactions
	protected String productId;
	protected String productName;
	protected String category;
	protected String client;
	protected String quantity;
	protected String code;
	
	//Initializing the TransactionsService
	public TransactionsBean(){
		transactionsService = new TransactionsService();
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	//Called when the button in entry.xhtml is clicked. Adds an Entry to the database using TransactionsService
	public String manageEntry(){
		transactionsService.manageEntry(this.productId, this.productName, this.category, this.client, this.quantity, this.code);
		return "index.xhtml?faces-redirect=true";
	}
	
	//Called when the button in retrieval.xhtml is clicked. Adds a Retrieval to the database
	//using TransactionsService
	public String manageRetrieval(){
		transactionsService.manageRetrieval(this.productId, this.productName, this.category, this.client, this.quantity, this.code);
		return "index.xhtml?faces-redirect=true";
	}
	
	
	
	
}
