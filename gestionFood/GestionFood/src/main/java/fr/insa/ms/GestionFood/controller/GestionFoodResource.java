package fr.insa.ms.GestionFood.controller;
import fr.insa.ms.GestionFood.model.*;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/self")
public class GestionFoodResource {
	private List<Plat> menu = new ArrayList<Plat>();
	private List<Bac> bacs = new ArrayList<Bac>();
	/*@PostConstruct
	public void init() {
		ListeFood menu = new ListeFood();
		menu.addPlatToMenu(new Plat(1,"Magret de canard","C BIG LE KANAR","CHAUD"));
		menu.addPlatToMenu(new Plat(2,"Tiramisu","Miam","FROID"));
	}*/
	
	//Partie menu & plats
	@GetMapping("/menu")
	public List<Plat> getMenu(){
		return menu;
	}

	
	/*public void ajouterPlat(@RequestBody int id, @RequestBody String nom, @RequestBody String description, @RequestBody String type) */
	@PostMapping("/addplat")
	public Plat ajouterPlat(@RequestBody Plat plat){
		menu.add(plat);
		return plat;
	}
	
	@DeleteMapping("/menu/{id}")
	public boolean deletePlat(@PathVariable int id) {
		return menu.removeIf(Plat -> Plat.getId()==id);
	}
	/*
	//Partie Bacs à food
	@GetMapping("/bacs")
	public List<Bac> getBacs(){
		return bacs;
	}
	@PostMapping("/addbac")
	public void ajouterBac(@RequestBody int id, @RequestBody int nom, @RequestBody String description, @RequestBody String type) {
		menu.add(new Plat(id,nom,description,type));
	}*/
}
