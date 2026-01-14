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

    // toutes les 10 secondes
    @Scheduled(fixedRate = 10000)
    public void updateOccupation() {

        List<OccupationTableEntity> tables = repository.findAll();

        for (OccupationTableEntity table : tables) {

            int occupants = 0;

            if (table.getEtat() == EtatTable.LIBRE || table.getEtat() == EtatTable.OCCUPEE) {
                occupants = capteurService.getOccupantsSimule(table.getJaugeMax());
            }

            table.setNombrePlaces(
                Math.max(table.getJaugeMax() - occupants, 0)
            );

            // Mise à jour de l'état
            if (occupants > 0) {
                table.setEtat(EtatTable.OCCUPEE);

            } else if (table.getEtat() == EtatTable.OCCUPEE) {
                table.setEtat(EtatTable.A_NETTOYER);

            } else if (table.getEtat() == EtatTable.A_NETTOYER) {
                table.setEtat(EtatTable.LIBRE);
            }else {
            	if (table.getEtat() == null) {
            		if (occupants > 0) {
            			table.setEtat(EtatTable.OCCUPEE);
            		}
            		else {
            			table.setEtat(EtatTable.LIBRE);
            		}
            	}
            }

            repository.save(table);
        }
    }
}

