package fr.insa.ms.gestTable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.gestTable.model.TableEntity;
import fr.insa.ms.gestTable.repository.TableRepository;


@RestController
public class GestTableResource {

    @Autowired
    private TableRepository tableRepository;

    @PostMapping("/addTable")
    public String newTable(
            @RequestParam int id,
            @RequestParam String nomReservation,
            @RequestParam int nombrePlaces,
            @RequestParam int jaugeMax) {

        TableEntity table = new TableEntity(id, nomReservation, nombrePlaces, jaugeMax);
        tableRepository.save(table);
        return "Table ajoutée en base.";
    }

    @DeleteMapping("/deleteTable/{id}")
    public String deleteTable(@PathVariable int id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return "Table supprimée.";
        }
        return "Table non trouvée.";
    }

    @PutMapping("/updateTable/{id}")
    public String updateTable(
            @PathVariable int id,
            @RequestParam String nomReservation,
            @RequestParam int nombrePlaces,
            @RequestParam int jaugeMax) {

        return tableRepository.findById(id).map(table -> {
            table.setNomReservation(nomReservation);
            table.setNombrePlaces(nombrePlaces);
            table.setJaugeMax(jaugeMax);
            tableRepository.save(table);
            return "Table modifiée avec succès.";
        }).orElse("Table non trouvée.");
    }

    @GetMapping("/listTables")
    public List<TableEntity> listAllTables() {
        return tableRepository.findAll();
    }

    @GetMapping("/infoTable/{id}")
    public TableEntity infoTable(@PathVariable int id) {
        return tableRepository.findById(id).orElse(null);
    }
}
