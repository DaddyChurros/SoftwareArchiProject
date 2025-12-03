package fr.insa.ms.GestionFood.model;

public class Plat {
	private int id;
	private String nom;
	private String description;
	private String type; //chaud ou froid
	
	//Constructor
	public Plat(int id, String nom, String description, String type) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
