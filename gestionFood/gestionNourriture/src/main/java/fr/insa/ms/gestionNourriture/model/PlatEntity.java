package fr.insa.ms.gestionNourriture.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plats")
public class PlatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String description;

    @Enumerated(EnumType.STRING)
    private Temperature type; // CHAUD / FROID

    public PlatEntity() {}

    public PlatEntity(String nom, String description, Temperature type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }
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
