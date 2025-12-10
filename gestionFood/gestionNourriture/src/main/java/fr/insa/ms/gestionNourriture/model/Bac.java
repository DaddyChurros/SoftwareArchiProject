package fr.insa.ms.gestionNourriture.model;


public class Bac {
	//Le bac est caractérisé par une id, son rôle attitré (plat chauds ou froids), sa température en fonction de seuils et le poids total avec la nourriture pour vérifier la disponibilité
	private int id;
	private int idPlat;
	private Temperature type; //chaud ou froid
	private double seuil_temp;
	//seuils de temperature
	public static final double SEUIL_FROID = 3.0; //Si le plat froid est au dessus de 3° alerte
	public static final double SEUIL_CHAUD = 65.0; // si le plat chaud est en dessous de 65° alerte 
	
	public Bac(int id, int idPlat, Temperature type, double seuil_temp) {
		super();
		this.id = id;
		this.idPlat = idPlat;
		this.type = type;
		this.seuil_temp = seuil_temp;
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

	public Temperature getType() {
		return type;
	}

	public void setType(Temperature type) {
		this.type = type;
	}

	public double getSeuil_temp() {
		return seuil_temp;
	}

	public void setSeuil_temp(double seuil_temp) {
		this.seuil_temp = seuil_temp;
	}
}
