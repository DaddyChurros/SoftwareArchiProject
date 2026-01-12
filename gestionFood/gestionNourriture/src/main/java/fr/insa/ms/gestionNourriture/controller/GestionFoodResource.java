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
	
	public GestionFoodResource(RestTemplate restTemplate, @Value("${securite-restaurant.url}") String securiteRestaurantBaseUrl) {
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
	@PostMapping("/bacs/{idBac}/temperature")
	public String majTemperatureBac(@PathVariable int idBac, @RequestBody Capteur mesure) {
		Bac bac = bacs.stream().filter(b->b.getId()==idBac).findFirst().orElse(null);
		if(bac == null) {
			return "Bac " + idBac + " introuvable";
		}
		Double temperature = mesure.getTemperature();
		if(temperature == null) {
			return "Température manquante dans la mesure";
		}
		//Verification des seuils en fonction du type de bac
		boolean alerte = false;
		String msg;
		if(bac.getType()== Temperature.FROID){
			// si temp > seuil pour froid  alors on leve une alerte
			if(temperature > bac.getSeuil_temp()) {
				alerte = true; 
				msg = "Température trop élevée pour bac froid " + idBac + " : " + temperature + "°C (seuil "+ bac.getSeuil_temp() + "°C";
			} else {
				msg = "Température OK pour bac froid " + idBac + " : " + temperature + "°C";
			}
		} else if(bac.getType()==Temperature.CHAUD) {
			//si temperature est inférieure à 65°C alerte levée
			if(temperature < bac.getSeuil_temp()) {
				alerte = true; 
				msg = "Température trop basse pour bac chaud " + idBac + " : " + temperature + "°C seuil "+ bac.getSeuil_temp() + "°C";
			} else {
				msg = "Température OK pour bac chaud" + idBac + " : " + temperature + "°C";
			}
		}
		else {
			msg = "Type de bac inconnu our bac " + idBac + " : " + bac.getType();
		}
		//Si alerte on appelle le microservice SecuRestau
		if (alerte) {
			envoyerAlerteTemperature(msg);
		}
		return msg;
	}
	//Méthodes d'envoi d'alerte 
	private void envoyerAlerteTemperature(String message) {
		AlerteRequest alert = new AlerteRequest();
		alert.setSourceService("GestionNourriture");
		alert.setMessage(message);
		alert.setSeverity(AlerteRequest.Severity.CRITICAL);
		
		String url = securiteRestaurantBaseUrl + "/food_temperature";
		System.out.println("URL appelée = " + url);
		restTemplate.postForEntity(url, alert, Void.class);
	}
}
