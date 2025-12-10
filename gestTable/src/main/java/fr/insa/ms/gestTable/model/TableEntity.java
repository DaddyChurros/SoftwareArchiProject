package fr.insa.ms.gestTable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables_restaurant")
public class TableEntity {

    @Id
    private int id;
    private String nomReservation;
    private int nombrePlaces;
    private int jaugeMax;

    public TableEntity() {}

    public TableEntity(int id, String nomReservation, int nombrePlaces, int jaugeMax) {
        this.id = id;
        this.nomReservation = nomReservation;
        this.nombrePlaces = nombrePlaces;
        this.jaugeMax = jaugeMax;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
