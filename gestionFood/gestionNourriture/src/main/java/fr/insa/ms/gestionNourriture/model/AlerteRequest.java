package fr.insa.ms.gestionNourriture.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AlerteRequest {
	
	@NotBlank
	private String sourceService;
	
	@NotBlank
	private String message;
	
	@NotNull
	private Severity severity;
	
	
	public enum Severity{
		LOW,
		MEDIUM,
		HIGH, 
		CRITICAL
	}

	//Getters & Setters
	public String getSourceService() {
		return sourceService;
	}


	public void setSourceService(String sourceService) {
		this.sourceService = sourceService;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Severity getSeverity() {
		return severity;
	}


	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
}