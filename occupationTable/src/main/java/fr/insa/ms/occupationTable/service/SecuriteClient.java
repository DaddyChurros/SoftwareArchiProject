package fr.insa.ms.occupationTable.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import fr.insa.ms.occupationTable.model.AlerteRequest;

@Service
public class SecuriteClient {
    
    private final RestTemplate restTemplate;
    
    // Nom du service dans Eureka (en MAJUSCULES)
    private static final String SECURITE_SERVICE = "http://SECURITERESTAURANT";
    
    public SecuriteClient(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void envoyerAlerte(String message, AlerteRequest.Severity severity) {
        try {
            AlerteRequest alerte = new AlerteRequest();
            alerte.setSourceService("OccupationTable");
            alerte.setMessage(message);
            alerte.setSeverity(severity);
            
            String url = SECURITE_SERVICE + "/securite/table_event";
            
            restTemplate.postForEntity(url, alerte, Void.class);
            
            System.out.println("Alerte envoyée avec succès à SecuriteRestaurant");
            
        } catch (RestClientException e) {
            System.err.println("Erreur lors de l'envoi de l'alerte : " + e.getMessage());
        }
    }
}