package fr.insa.ms.SecuriteRestaurant.controller;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import fr.insa.ms.SecuriteRestaurant.model.*;


@RestController
@RequestMapping("/securite")
public class SecuriteRestaurantResource {
	private final Historique historique;
	public SecuriteRestaurantResource(Historique historique) {
		this.historique= historique;
	}
	//Probleme trop de client
	@PostMapping("/client_overflow")
	@ResponseStatus(HttpStatus.CREATED)
	public AlerteEntity handleClientOverflow(@Valid @RequestBody AlerteRequest request) {
		return historique.createAlerte(AlerteType.CLIENT_OVERFLOW, request);
	}
	//Probleme plat vide
	@PostMapping("/food_empty")
	@ResponseStatus(HttpStatus.CREATED)
	public AlerteEntity handleFoodEmpty(@Valid @RequestBody AlerteRequest request) {
		return historique.createAlerte(AlerteType.FOOD_EMPTY, request);
	}
	//Probleme Mauvaise température
	@PostMapping("/food_temperature")
	@ResponseStatus(HttpStatus.CREATED)
	public AlerteEntity handleFoodTemperature(@Valid @RequestBody AlerteRequest request) {
		return historique.createAlerte(AlerteType.FOOD_TEMPERATURE, request);
	}
	//Probleme gestion table
	@PostMapping("/table_event")
	@ResponseStatus(HttpStatus.CREATED)
	public AlerteEntity handleTableEvent(@Valid @RequestBody AlerteRequest request) {
		return historique.createAlerte(AlerteType.TABLE_EVENT, request);
	}
	//Historique des alertes
	@GetMapping("/alertes")
	public List<AlerteEntity> getAllAlertes(){
		return historique.getAllAlertes();
	}
}
