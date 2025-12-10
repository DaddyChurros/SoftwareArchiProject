package fr.insa.ms.gestionNourriture.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<Plat> plats;
	
	public Menu() {
		this.plats= new ArrayList<Plat>();
	}
	public List<Plat> getListePlat(){
		return plats;
	}
	public void addPlatToMenu(Plat plat) {
		this.plats.add(plat);
	}
}
