package fr.insa.ms.gestionTable.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.gestionTable.model.*;


/*Ajouter/supprimer des tables
Définir la jauge max d’occupants par table.
Modifier une table (ex : nombre de places, nom, emplacement)
Retourner les informations d’une table
Lister les tables d’une salle.*/

/*
private int id;
private String nomReservation;
private int nombrePlaces;
private int jaugeMax;*/

@RestController
public class GestionTableResource {

    private List<Table> tables = new ArrayList<>();

    @PostMapping("/addTable")
    public void newTable(@RequestParam int id, @RequestParam String nomReservation, @RequestParam int nombrePlaces, @RequestParam int jaugeMax) {
        tables.add(new Table(id, nomReservation, nombrePlaces, jaugeMax));
    }

    @DeleteMapping("/deleteTable/{id}")
    public void deleteTable(@PathVariable int id) {
        tables.removeIf(table -> table.getId() == id);
    }

    @PutMapping("/updateTable/{id}")
    public String updateTable(@PathVariable int id, @RequestParam String nomReservation, @RequestParam int nombrePlaces, @RequestParam int jaugeMax) {

        Optional<Table> tableOptional = tables.stream().filter(table -> table.getId() == id).findFirst();
        
        if (tableOptional.isPresent()) {
            Table table = tableOptional.get();
            table.setNomReservation(nomReservation);
            table.setNombrePlaces(nombrePlaces);
            table.setJaugeMax(jaugeMax);
            return "Table modifiée avec succès.";
        } else {
            return "Table non trouvée.";
        }
    }
    
    @GetMapping("/listTables")
    public List<Table> listAllTables() {
        return tables; 
    }
    
    @GetMapping("/infoTable/{id}")
    public Table infoTable(@PathVariable int id) {

        Optional<Table> tableOptional = tables.stream().filter(table -> table.getId() == id).findFirst();
        
        if (tableOptional.isPresent()) {
            Table table = tableOptional.get();
            return table;
        } else {
            return null;
        }
    }
}

