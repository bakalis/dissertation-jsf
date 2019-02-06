package com.bakalis.jsf.managedBeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bakalis.jsf.services.ContentsService;
import com.bakalis.models.Product;
import com.bakalis.models.SingleTransaction;

@ManagedBean(name = "contents", eager = true)
@ViewScoped
public class ContentsBean {

	protected ContentsService contentsService;
	protected String searchBar;
	protected String searchFilter;
	private boolean searched = false;
	
	
	public boolean isSearched() {
		return searched;
	}


	public void setSearched(boolean searched) {
		this.searched = searched;
	}


	public String getSearchBar() {
		return searchBar;
	}


	public void setSearchBar(String searchBar) {
		this.searchBar = searchBar;
	}


	public String getSearchFilter() {
		return searchFilter;
	}


	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}


	public ContentsBean() {
		contentsService = new ContentsService();
	}
		
	
   public ArrayList<Product> getContents(){
	   this.setSearched(false);
	   return contentsService.getAllContents();
   }
   
   public ArrayList<Product> getSearchedContents(){
	   this.setSearched(true);
	   return contentsService.getSearchedContents(this.getSearchBar(), this.getSearchFilter());
	  
   }
   
   public ArrayList<SingleTransaction> getPastTransactions(){
		return contentsService.getPastTransactions();
   }
  
}
