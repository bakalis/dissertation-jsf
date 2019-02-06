package com.bakalis.jsf.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@ManagedBean(name="auth", eager=true)
@SessionScoped
public class AuthenticationBean {
	//Provides all the information needed about the Session
	protected Authentication auth;
	
	
	//Returns the auth object from the Spring Security Context Holder
	public Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}

	public AuthenticationBean(){}

	
	//Destroys the session. Called when the user clicks the appropriate Link in the Navigation bar
	public String logout(){
		SecurityContextHolder.clearContext();
		return "index.xhtml?faces-redirect=true";
	}
}
