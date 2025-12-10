package fr.insa.ms.gestionTable.model;

public class Table {
	
	private int id;
	private String nomReservation;
    private int nombrePlaces;
    private int jaugeMax;
	
	
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

	
    public Table(int id, String nom, int nombrePlaces, int jaugeMax) {
        this.id = id;
        this.nomReservation = nom;
        this.nombrePlaces = nombrePlaces;
        this.jaugeMax = jaugeMax;
    }

}
