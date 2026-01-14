package fr.insa.ms.gestionNourriture.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bacs")
public class BacEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idPlat;

    @Enumerated(EnumType.STRING)
    private Temperature type;

    private double seuilTemp;

    public BacEntity() {}

    public BacEntity(int idPlat, Temperature type, double seuilTemp) {
        this.idPlat = idPlat;
        this.type = type;
        this.seuilTemp = seuilTemp;
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

	public double getSeuilTemp() {
		return seuilTemp;
	}

	public void setSeuilTemp(double seuilTemp) {
		this.seuilTemp = seuilTemp;
	}
}
