package fr.insa.ms.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {
    
    private final RestTemplate restTemplate;
    
    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    // ==================== GESTION FOOD / MENU ====================
    
    @GetMapping("/food/menu")
    public Object getMenu() {
        return restTemplate.getForObject(
            "http://GestionFood/self/menu",
            Object.class
        );
    }
    
    @PostMapping("/food/menu")
    public Object ajouterPlat(@RequestBody Object plat) {
        return restTemplate.postForObject(
            "http://GestionFood/self/menu",
            plat,
            Object.class
        );
    }
    
    @DeleteMapping("/food/menu/{id}")
    public Object supprimerPlat(@PathVariable int id) {
        restTemplate.delete("http://GestionFood/self/menu/" + id);
        return "Plat supprimé";
    }
    
    // ==================== GESTION FOOD / BACS ====================
    
    @GetMapping("/food/bacs")
    public Object getBacs() {
        return restTemplate.getForObject(
            "http://GestionFood/self/bacs",
            Object.class
        );
    }
    
    @PostMapping("/food/bacs")
    public Object ajouterBac(@RequestBody Object bac) {
        return restTemplate.postForObject(
            "http://GestionFood/self/bacs",
            bac,
            Object.class
        );
    }
    
    // ==================== GESTION FOOD / CAPTEURS ====================
    
    @PostMapping("/food/bacs/{idBac}/temperature")
    public Object majTemperatureBac(@PathVariable int idBac, @RequestBody Object mesure) {
        return restTemplate.postForObject(
            "http://GestionFood/self/bacs/" + idBac + "/temperature",
            mesure,
            String.class
        );
    }
    
    // ==================== GESTION TABLE ====================
    
    @GetMapping("/tables")
    public Object listTables() {
        return restTemplate.getForObject(
            "http://gestTable/listTables",
            Object.class
        );
    }
    
    @GetMapping("/tables/{id}")
    public Object infoTable(@PathVariable int id) {
        return restTemplate.getForObject(
            "http://gestTable/infoTable/" + id,
            Object.class
        );
    }
    
    @PostMapping("/tables")
    public Object addTable(
            @RequestParam int id,
            @RequestParam String nomReservation,
            @RequestParam int nombrePlaces,
            @RequestParam int jaugeMax) {
        String url = String.format(
            "http://gestTable/addTable?id=%d&nomReservation=%s&nombrePlaces=%d&jaugeMax=%d",
            id, nomReservation, nombrePlaces, jaugeMax
        );
        return restTemplate.postForObject(url, null, String.class);
    }
    
    @DeleteMapping("/tables/{id}")
    public Object deleteTable(@PathVariable int id) {
        restTemplate.delete("http://gestTable/deleteTable/" + id);
        return "Table supprimée.";
    }
    
    @PutMapping("/tables/{id}")
    public Object updateTable(
            @PathVariable int id,
            @RequestParam String nomReservation,
            @RequestParam int nombrePlaces,
            @RequestParam int jaugeMax) {
        String url = String.format(
            "http://gestTable/updateTable/%d?nomReservation=%s&nombrePlaces=%d&jaugeMax=%d",
            id, nomReservation, nombrePlaces, jaugeMax
        );
        restTemplate.put(url, null);
        return "Table modifiée avec succès.";
    }
    
    // ==================== OCCUPATION TABLE ====================
    
    @GetMapping("/occupation-tables")
    public Object listOccupationTables() {
        return restTemplate.getForObject(
            "http://occupationTable/occupationTable/list",
            Object.class
        );
    }
    
    @GetMapping("/occupation-tables/{id}")
    public Object infoOccupationTable(@PathVariable int id) {
        return restTemplate.getForObject(
            "http://occupationTable/occupationTable/info/" + id,
            Object.class
        );
    }
    
    // ==================== SECURITE RESTAURANT ====================
    
    @PostMapping("/securite/client-overflow")
    @ResponseStatus(HttpStatus.CREATED)
    public Object handleClientOverflow(@RequestBody Object request) {
        return restTemplate.postForObject(
            "http://securiteRestaurant/securite/client_overflow",
            request,
            Object.class
        );
    }
    
    @PostMapping("/securite/food-empty")
    @ResponseStatus(HttpStatus.CREATED)
    public Object handleFoodEmpty(@RequestBody Object request) {
        return restTemplate.postForObject(
            "http://securiteRestaurant/securite/food_empty",
            request,
            Object.class
        );
    }
    
    @PostMapping("/securite/food-temperature")
    @ResponseStatus(HttpStatus.CREATED)
    public Object handleFoodTemperature(@RequestBody Object request) {
        return restTemplate.postForObject(
            "http://securiteRestaurant/securite/food_temperature",
            request,
            Object.class
        );
    }
    
    @PostMapping("/securite/table-event")
    @ResponseStatus(HttpStatus.CREATED)
    public Object handleTableEvent(@RequestBody Object request) {
        return restTemplate.postForObject(
            "http://securiteRestaurant/securite/table_event",
            request,
            Object.class
        );
    }
    
    @GetMapping("/securite/alertes")
    public Object getAllAlertes() {
        return restTemplate.getForObject(
            "http://securiteRestaurant/securite/alertes",
            Object.class
        );
    }
}