package fr.insa.ms.gestionNourriture.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.gestionNourriture.model.*;
import fr.insa.ms.gestionNourriture.model.BacEntity;
import fr.insa.ms.gestionNourriture.repository.BacRepository;
import fr.insa.ms.gestionNourriture.repository.PlatRepository;

@RestController
@RequestMapping("/self")
public class GestionFoodResource {
	@Autowired
	private PlatRepository platRepository;

	@Autowired
	private BacRepository bacRepository;

	private final RestTemplate restTemplate;
	private final String securiteRestaurantBaseUrl;
	
	public GestionFoodResource(RestTemplate restTemplate, @Value("${securite-restaurant.url}") String securiteRestaurantBaseUrl) {
		this.restTemplate=restTemplate;
		this.securiteRestaurantBaseUrl= securiteRestaurantBaseUrl;
	}
	
	@GetMapping("/menu")
    public List<PlatEntity> getMenu() {
        return platRepository.findAll();
    }
	

    @PostMapping("/menu")
    public PlatEntity ajouterPlat(@RequestBody PlatEntity plat) {
        return platRepository.save(plat);
    }

	
    @DeleteMapping("/menu/{id}")
    public String supprimerPlat(@PathVariable int id) {
        if (platRepository.existsById(id)) {
            platRepository.deleteById(id);
            return "Plat supprimé";
        }
        return "Plat non trouvé";
    }
	//Partie Bacs à food 
    @GetMapping("/bacs")
    public List<BacEntity> getBacs() {
        return bacRepository.findAll();
    }
	
	@PostMapping("/bacs")
	public BacEntity ajouterBac(@RequestBody BacEntity bac) {
	    return bacRepository.save(bac);
	}
	//Partie capteurs / alertes
	@PostMapping("/bacs/{idBac}/temperature")
	public String majTemperatureBac(@PathVariable int idBac, @RequestBody Capteur mesure) {

	    BacEntity bac = bacRepository.findById(idBac).orElse(null);
	    if (bac == null) {
	        return "Bac " + idBac + " introuvable";
	    }

	    Double temperature = mesure.getTemperature();
	    if (temperature == null) {
	        return "Température manquante dans la mesure";
	    }

	    boolean alerte = false;
	    String msg;

	    if (bac.getType() == Temperature.FROID) {
	        if (temperature > bac.getSeuilTemp()) {
	            alerte = true;
	            msg = "Température trop élevée pour bac froid " + idBac +
	                  " : " + temperature + "°C (seuil " + bac.getSeuilTemp() + "°C)";
	        } else {
	            msg = "Température OK pour bac froid " + idBac + " : " + temperature + "°C";
	        }

	    } else if (bac.getType() == Temperature.CHAUD) {
	        if (temperature < bac.getSeuilTemp()) {
	            alerte = true;
	            msg = "Température trop basse pour bac chaud " + idBac +
	                  " : " + temperature + "°C (seuil " + bac.getSeuilTemp() + "°C)";
	        } else {
	            msg = "Température OK pour bac chaud " + idBac + " : " + temperature + "°C";
	        }

	    } else {
	        msg = "Type de bac inconnu pour bac " + idBac + " : " + bac.getType();
	    }

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
