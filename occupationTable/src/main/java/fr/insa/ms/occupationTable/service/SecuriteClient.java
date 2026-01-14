package fr.insa.ms.occupationTable.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecuriteClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String SECURITE_URL = "http://localhost:8083/alerteTableLibre";

    public void alerterTableLibre(int tableId) {
        restTemplate.postForObject(
                SECURITE_URL + "?tableId=" + tableId,
                null,
                String.class
        );
    }
}
