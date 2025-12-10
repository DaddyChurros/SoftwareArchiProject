package fr.insa.ms.SecuriteRestaurant.model;

import java.time.LocalTime;

public class Alerte {
	private long id; 
	private AlerteType type;
	private String sourceService;
	private String message;
	private AlerteRequest.Severity severity;
	private LocalTime date;
	
	public Alerte(long id, AlerteType type, String sourceService, String message, LocalTime date) {
		super();
		this.id = id;
		this.type = type;
		this.sourceService = sourceService;
		this.message = message;
		this.date = date;
	}

	public Alerte() {
		super();
	}

	//Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AlerteType getType() {
		return type;
	}
	public void setType(AlerteType type) {
		this.type = type;
	}
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
	public AlerteRequest.Severity getSeverity() {
		return severity;
	}

	public void setSeverity(AlerteRequest.Severity severity) {
		this.severity = severity;
	}
	public LocalTime getDate() {
		return date;
	}
	public void setDate(LocalTime date) {
		this.date = date;
	}
}
