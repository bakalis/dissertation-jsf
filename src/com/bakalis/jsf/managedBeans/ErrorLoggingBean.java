package com.bakalis.jsf.managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.bakalis.jsf.services.ErrorLoggingService;


@ManagedBean(name = "error", eager = true)
@ApplicationScoped
public class ErrorLoggingBean {

	ErrorLoggingService errorService;
	String error;
	
	public String getError() {
		return this.getServiceError();
	}

	public void setError(String error) {
		setServiceError(error);
		this.error = error;
	}
	
	//Initializing the Error Service
	public ErrorLoggingBean(){
		errorService = new ErrorLoggingService();
	}
	
	public String getServiceError(){		
		return errorService.getError();
	}
	
	public void setServiceError(String error){
		errorService.setError(error);
	}
	
	//Simple method to reset the Error. Used typically when Services are first Called.
	public void reset(){
		errorService.reset();
	}
}
