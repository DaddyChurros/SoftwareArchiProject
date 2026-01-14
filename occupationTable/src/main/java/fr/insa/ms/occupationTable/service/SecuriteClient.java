package fr.insa.ms.occupationTable.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.occupationTable.model.AlerteRequest;

@Service
public class SecuriteClient {

    private final RestTemplate restTemplate;
    private final String securiteRestaurantBaseUrl;

    public SecuriteClient(
        RestTemplate restTemplate,
        @Value("${securite-restaurant.url}") String securiteRestaurantBaseUrl
    ) {
        this.restTemplate = restTemplate;
        this.securiteRestaurantBaseUrl = securiteRestaurantBaseUrl;
    }

    public void envoyerAlerte(String message, AlerteRequest.Severity severity) {
        AlerteRequest alerte = new AlerteRequest();
        alerte.setSourceService("OccupationTable");
        alerte.setMessage(message);
        alerte.setSeverity(severity);

        String url = securiteRestaurantBaseUrl + "/table_event";
        restTemplate.postForEntity(url, alerte, Void.class);
    }
}
