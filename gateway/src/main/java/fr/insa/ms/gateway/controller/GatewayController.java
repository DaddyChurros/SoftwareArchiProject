package fr.insa.ms.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ---- GESTION FOOD ----
    @GetMapping("/food/menu")
    public Object getMenu() {
        return restTemplate.getForObject(
            "http://localhost:8082/self/menu",
            Object.class
        );
    }

    @PostMapping("/food/plat")
    public Object addPlat(@RequestBody Object plat) {
        return restTemplate.postForObject(
            "http://localhost:8082/self/plat",
            plat,
            Object.class
        );
    }

    // ---- GESTION TABLE ----
    @GetMapping("/tables")
    public Object listTables() {
        return restTemplate.getForObject(
            "http://localhost:8081/listTables",
            Object.class
        );
    }

    @GetMapping("/tables/{id}")
    public Object infoTable(@PathVariable int id) {
        return restTemplate.getForObject(
            "http://localhost:8081/infoTable/" + id,
            Object.class
        );
    }
}
