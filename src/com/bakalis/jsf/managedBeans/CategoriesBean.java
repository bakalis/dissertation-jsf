package com.bakalis.jsf.managedBeans;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.bakalis.jsf.services.ContentsService;
import com.bakalis.jsf.services.TransactionsService;
import com.bakalis.models.Category;

@ManagedBean(name = "categories", eager = true)
@SessionScoped //Data shared between Views
public class CategoriesBean {

	//Bound to the searchBar input in categories.xhtml
	protected String searchBar;
	//Its value determines if the viewed categories are the results of a search
	protected boolean searched;
	
	
	protected ContentsService contentsService;
	protected TransactionsService transactionsService;
	
	//Stores the Data of the Category to edit
	protected Category editedCategory;

	//the Id of the Category to be Added
	protected String newCategoryId;
	//the Name of the Category to be Added
	protected String newCategoryName;
	
	
	public String getNewCategoryId() {
		return newCategoryId;
	}


	public void setNewCategoryId(String newCategoryId) {
		this.newCategoryId = newCategoryId;
	}


	public String getNewCategoryName() {
		return newCategoryName;
	}


	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}


	public void setEditedCategory(Category editedCategory) {
		this.editedCategory = editedCategory;
	}

	// Initializing the Services objects
	public CategoriesBean() {
		contentsService = new ContentsService();
		transactionsService = new TransactionsService();
	}
	
	
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


	//Returns the List of All Categories, using the ContentsService
	public ArrayList<Category> getCategories(){
		this.setSearched(false);
		this.setEditedCategory(null);
		return contentsService.getCategories();
	}
	//Returns the List of Searched Categories, using the Data in the view and ContentsService
	public ArrayList<Category> getSearchedCategories(){
		this.setSearched(true);
		this.setEditedCategory(null);
		return contentsService.getSearchedCategories(this.getSearchBar());
	}
	
	public Category getEditedCategory(){
		return this.editedCategory;
	}
	
	//Called upon clicking the Edit link on a Category. Sets the Data of the Category to be Edited
	//and Redirects to the appropriate Page
	public String editedCategory(){
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	    String editedId =  params.get("editedId"); 
	    this.setEditedCategory(contentsService.getEditedCategory(editedId));
		return "addCategory.xhtml?faces-redirect=true";
	}
	
	
	//Called upon clicking the Delete link on a Category. Deletes the Category using the TransactionsService
	public String deleteCategory(){
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	    String deleteId =  params.get("deleteId");
	    transactionsService.deleteCategory(deleteId);
	    return null;
	}
	
	//Called upon clicking the Edit Category button. Uses the editedCategory object to store the appropriate data
	//using TransactionsService
	public String editCategory(){
		transactionsService.editCategory(String.valueOf(this.getEditedCategory().getId()), this.getEditedCategory().getCategoryName());
		this.setEditedCategory(null);
		return "categories.xhtml?faces-redirect=true";
	}
	
	//Called upon clicking the Add Category button. Uses the newCategory Strings to store the appropriate data
	//using TransactionsService
	public String addCategory(){
		transactionsService.addCategory(this.newCategoryId, this.newCategoryName);
		this.setNewCategoryId(null);
		this.setNewCategoryName(null);
		return "categories.xhtml?faces-redirect=true";
	}
	
}
