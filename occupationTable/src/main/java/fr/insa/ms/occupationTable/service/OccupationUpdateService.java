package fr.insa.ms.occupationTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import fr.insa.ms.occupationTable.model.*;
import fr.insa.ms.occupationTable.repository.OccupationTableRepository;

import java.util.List;

@Service
public class OccupationUpdateService {

    @Autowired
    private OccupationTableRepository repository;

    @Autowired
    private CapteurSimulatorService capteurService;

    @Autowired
    private SecuriteClient securiteClient;

    @Scheduled(fixedRate = 10000)
    public void updateOccupation() {

        List<OccupationTableEntity> tables = repository.findAll();

        for (OccupationTableEntity table : tables) {

            EtatTable ancienEtat = table.getEtat();
            int occupants = 0;

            if (ancienEtat == EtatTable.LIBRE || ancienEtat == EtatTable.OCCUPEE) {
                occupants = capteurService.getOccupantsSimule(table.getJaugeMax());
            }

            table.setNombrePlaces(
                Math.max(table.getJaugeMax() - occupants, 0)
            );

            // Détermination nouvel état
            if (occupants > 0) {
                table.setEtat(EtatTable.OCCUPEE);
            } else if (ancienEtat == EtatTable.OCCUPEE) {
                table.setEtat(EtatTable.A_NETTOYER);
            } else if (ancienEtat == EtatTable.A_NETTOYER) {
                table.setEtat(EtatTable.LIBRE);
            } else if (ancienEtat == null) {
                table.setEtat(occupants > 0 ? EtatTable.OCCUPEE : EtatTable.LIBRE);
            }

            // ================= ALERTES =================

            // Table pleine
            if (table.getNombrePlaces() == 0) {
                securiteClient.envoyerAlerte(
                    "Table " + table.getId() + " pleine (" + table.getJaugeMax() + " places occupées)",
                    AlerteRequest.Severity.HIGH
                );
            }

            // Changement d'état significatif
            if (ancienEtat != table.getEtat()) {
                securiteClient.envoyerAlerte(
                    "Table " + table.getId() + " : " + ancienEtat + " → " + table.getEtat(),
                    AlerteRequest.Severity.LOW
                );
            }

            repository.save(table);
        }
    }
}


