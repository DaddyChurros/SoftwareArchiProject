package fr.insa.ms.gestionNourriture.model;

public class Capteur {
	private String idCapteur;
	private Double temperature_val;
	
	public Capteur() {
		
	}
	
	public Capteur(String idCapteur, Double temperature_val) {
		this.idCapteur = idCapteur;
		this.temperature_val = temperature_val;
	}

	public String getIdCapteur() {
		return idCapteur;
	}

	public void setIdCapteur(String idCapteur) {
		this.idCapteur = idCapteur;
	}

	public Double getTemperature() {
		return temperature_val;
	}

	public void setTemperature(Double temperature) {
		this.temperature_val = temperature;
	}
}
