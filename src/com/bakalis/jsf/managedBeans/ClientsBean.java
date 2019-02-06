package com.bakalis.jsf.managedBeans;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.bakalis.jsf.services.ContentsService;
import com.bakalis.jsf.services.TransactionsService;
import com.bakalis.models.Client;

@ManagedBean(name = "clients", eager = true)
@SessionScoped //Data shared between Views
public class ClientsBean {

	//Bound to the searchBar input in clients.xhtml
	protected String searchBar;
	//Its value determines if the viewed clients are the results of a search
	protected boolean searched;
	
	protected ContentsService contentsService;
	protected TransactionsService transactionsService;
	
	//Stores the Data of the Client to edit
	protected Client editedClient;

	//the Id of the Client to be Added
	protected String newClientId;
	//the Name of the Client to be Added
	protected String newClientName;
	
	
	public String getNewClientId() {
		return newClientName;
	}


	public void setNewClientId(String newClientId) {
		this.newClientId = newClientId;
	}


	public String getNewClientName() {
		return newClientName;
	}


	public void setNewClientName(String newClientName) {
		this.newClientName = newClientName;
	}


	public void setEditedClient(Client editedClient) {
		this.editedClient = editedClient;
	}

	// Initializing the Services objects
	public ClientsBean() {
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


	//Returns the List of All Clients, using the ContentsService
	public ArrayList<Client> getClients(){
		this.setSearched(false);
		this.setEditedClient(null);
		return contentsService.getClients();
	}
	//Returns the List of Searched Clients, using the Data in the view and ContentsService
	public ArrayList<Client> getSearchedClients(){
		this.setSearched(true);
		this.setEditedClient(null);
		return contentsService.getSearchedClients(this.getSearchBar());
	}
	
	public Client getEditedClient(){
		return this.editedClient;
	}
	
	//Called upon clicking the Edit link on a Client. Sets the Data of the Client to be Edited
	//and Redirects to the appropriate Page
	public String editedClient(){
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	    String editedId =  params.get("editedId"); 
	    this.setEditedClient(contentsService.getEditedClient(editedId));
		return "addClient.xhtml?faces-redirect=true";
	}
	
	//Called upon clicking the Delete link on a Client. Deletes the Client using the TransactionsService
	public String deleteClient(){
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	    String deleteId =  params.get("deleteId");
	    transactionsService.deleteClient(deleteId);
	    return null;
	}
	
	//Called upon clicking the Edit Client button. Uses the editedClient object to store the appropriate data
	//using TransactionsService
	public String editClient(){
		transactionsService.editClient(String.valueOf(this.getEditedClient().getId()), this.getEditedClient().getClientName());
		this.setEditedClient(null);
		return "clients.xhtml?faces-redirect=true";
	}
	
	//Called upon clicking the Add Client button. Uses the newClient Strings to store the appropriate data
	//using TransactionsService
	public String addClient(){
		transactionsService.addClient(this.newClientId, this.newClientName);
		this.setNewClientId(null);
		this.setNewClientName(null);
		return "clients.xhtml?faces-redirect=true";
	}
	
}
