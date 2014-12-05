package com.newwess.agendaalunos.controller;

import javax.annotation.ManagedBean;

@ManagedBean(value="mainController")
public class MainController {
	private String message;
	
	public MainController(){
		this.message = "AGENDA ALUNOS JSF XHTML";
	}
	
	public String getMessage(){
		return message;
	}

}
