package fr.insa.ms.SecuriteRestaurant.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.insa.ms.SecuriteRestaurant.repository.AlerteRepository;

@Service
public class Historique {

    private final AlerteRepository alerteRepository;

    public Historique(AlerteRepository alerteRepository) {
        this.alerteRepository = alerteRepository;
    }

    public AlerteEntity createAlerte(AlerteType type, AlerteRequest request) {

        AlerteEntity alerte = new AlerteEntity();
        alerte.setType(type);
        alerte.setSourceService(request.getSourceService());
        alerte.setMessage(request.getMessage());
        alerte.setSeverity(request.getSeverity());
        alerte.setDate(LocalDateTime.now());

        triggerNotification(alerte);
        return alerteRepository.save(alerte);
    }

    public List<AlerteEntity> getAllAlertes() {
        return alerteRepository.findAll();
    }

    private void triggerNotification(AlerteEntity alerte) {
        System.out.println(
            "ALERTE " + alerte.getSeverity() + " - " + alerte.getMessage()
        );
    }
}
