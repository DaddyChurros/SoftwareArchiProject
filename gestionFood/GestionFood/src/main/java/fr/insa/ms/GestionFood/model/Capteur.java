package fr.insa.ms.GestionFood.model;

public class Capteur {
	private String idCapteur;
	private Double temperature;
	
	public Capteur(String idCapteur, Double temperature) {
		super();
		this.idCapteur = idCapteur;
		this.temperature = temperature;
	}

	public String getIdCapteur() {
		return idCapteur;
	}

	public void setIdCapteur(String idCapteur) {
		this.idCapteur = idCapteur;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
}
