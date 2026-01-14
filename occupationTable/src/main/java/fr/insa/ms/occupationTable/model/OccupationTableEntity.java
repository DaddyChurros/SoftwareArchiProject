package fr.insa.ms.occupationTable.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables_restaurant")
public class OccupationTableEntity {

    @Id
    private int id;

    private String nomReservation;
    private int nombrePlaces; // places restantes
    private int jaugeMax;     // nombre max de places
    @Enumerated(EnumType.STRING)
    private EtatTable etat;

    public OccupationTableEntity() {}

    public OccupationTableEntity(int id, String nomReservation, int nombrePlaces, int jaugeMax, EtatTable etat) {
        this.id = id;
        this.setNomReservation(nomReservation);
        this.nombrePlaces = nombrePlaces;
        this.jaugeMax = jaugeMax;
        this.etat = etat;
    }

	public String getNomReservation() {
		return nomReservation;
	}

	public void setNomReservation(String nomReservation) {
		this.nomReservation = nomReservation;
	}

	public int getNombrePlaces() {
		return nombrePlaces;
	}

	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	public int getJaugeMax() {
		return jaugeMax;
	}

	public void setJaugeMax(int jaugeMax) {
		this.jaugeMax = jaugeMax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtatTable getEtat() {
		return etat;
	}

	public void setEtat(EtatTable etat) {
		this.etat = etat;
	}

}
