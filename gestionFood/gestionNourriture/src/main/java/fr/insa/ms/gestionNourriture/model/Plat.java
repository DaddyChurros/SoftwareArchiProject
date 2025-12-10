package fr.insa.ms.gestionNourriture.model;

public class Plat {
	private int id;
	private String nom;
	private String description;
	private Temperature type; //chaud ou froid
	//Constructor
	public Plat(int id, String nom, String description, Temperature type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.type = type;
	}
	public Plat() {}
	
	//getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Temperature getType() {
		return type;
	}

	public void setType(Temperature type) {
		this.type = type;
	}

	
}
