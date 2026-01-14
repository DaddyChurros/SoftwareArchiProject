package fr.insa.ms.SecuriteRestaurant.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "alertes")
public class AlerteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlerteType type;

    private String sourceService;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private AlerteRequest.Severity severity;

    private LocalDateTime date;

    public AlerteEntity(){};



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
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
