package com.bakalis.jsf.services;

/*
 * 
 * Simple Service that Keeps an Error String to be checked by and Used in other Services and Controllers.
 * 
 * */
public class ErrorLoggingService {

	protected String error = null;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	public boolean errorExists(){
		if(error==null)
			return false;
		else
			return true;
	}
	
	public void reset(){
		this.setError(null);
	}
		
	
}
