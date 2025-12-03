package fr.insa.ms.GestionFood.model;

public class Bac {
	//Le bac est caractérisé par une id, son rôle attitré (plat chauds ou froids), sa température en fonction de seuils et le poids total avec la nourriture pour vérifier la disponibilité
	private int id;
	private int idPlat;
	private String type; //chaud ou froid
	private double seuil_temp;
	public double seuil_froid = 3.0;
	public double seuil_chaud = 65.0;
	
	public Bac(int id, int idPlat, String type, double seuil_temp, double seuil_froid, double seuil_chaud) {
		super();
		this.id = id;
		this.idPlat = idPlat;
		this.type = type;
		this.seuil_temp = seuil_temp;
		this.seuil_froid = seuil_froid;
		this.seuil_chaud = seuil_chaud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPlat() {
		return idPlat;
	}

	public void setIdPlat(int idPlat) {
		this.idPlat = idPlat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSeuil_temp() {
		return seuil_temp;
	}

	public void setSeuil_temp(double seuil_temp) {
		this.seuil_temp = seuil_temp;
	}

	public double getSeuil_froid() {
		return seuil_froid;
	}

	public void setSeuil_froid(double seuil_froid) {
		this.seuil_froid = seuil_froid;
	}

	public double getSeuil_chaud() {
		return seuil_chaud;
	}

	public void setSeuil_chaud(double seuil_chaud) {
		this.seuil_chaud = seuil_chaud;
	}
		
	
}
