package fr.insa.ms.GestionFood.model;

import java.util.ArrayList;
import java.util.List;

public class ListeFood {
	List<Plat> plats;
	
	public ListeFood() {
		this.plats= new ArrayList<Plat>();
	}
	public List<Plat> getListePlat(){
		return plats;
	}
	public void addPlatToMenu(Plat plat) {
		this.plats.add(plat);
	}
}
