package fr.insa.ms.gestionNourriture.controller;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.gestionNourriture.model.*;

@RestController
@RequestMapping("/self")
public class GestionFoodResource {
	private List<Plat> menu = new ArrayList<Plat>();
	private List<Bac> bacs = new ArrayList<Bac>();
	private final RestTemplate restTemplate;
	private final String securiteRestaurantBaseUrl;
	
	public GestionFoodResource(RestTemplate restTemplate, @Value("${securite-restaurant.url}") String securiteRestaurantBaserl) {
		this.restTemplate=restTemplate;
		this.securiteRestaurantBaseUrl= securiteRestaurantBaseUrl;
	}
	
	//Partie menu & plats
	@GetMapping("/menu")
	public List<Plat> getMenu(){
		return menu;
	}

	
	/*public void ajouterPlat(@RequestBody int id, @RequestBody String nom, @RequestBody String description, @RequestBody String type) */
	@PostMapping("/plat")
	public Plat ajouterPlat(@RequestBody Plat plat){
		menu.add(plat);
		return plat;
	}
	
	@DeleteMapping("/menu/{id}")
	public boolean deletePlat(@PathVariable int id) {
		return menu.removeIf(Plat -> Plat.getId()==id);
	}
	
	//Partie Bacs à food 
	@GetMapping("/bacs")
	public List<Bac> getBacs(){
		return bacs;
	}
	
	@PostMapping("/addbac")
	public Bac ajouterBac(@RequestBody Bac bac) {
		bacs.add(bac);
		return bac;
	}
	//Partie capteurs / alertes
	public String majTemperatureBac(@PathVariable int idBac, @RequestBody Capteur mesure) {
		//Bac bac = bac.stream()
		return "todo";
	}
}
