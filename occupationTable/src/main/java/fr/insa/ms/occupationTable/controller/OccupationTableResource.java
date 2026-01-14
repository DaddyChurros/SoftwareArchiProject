package fr.insa.ms.occupationTable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fr.insa.ms.occupationTable.model.OccupationTableEntity;
import fr.insa.ms.occupationTable.repository.OccupationTableRepository;

import java.util.List;

@RestController
@RequestMapping("/occupationTable")
public class OccupationTableResource {

    @Autowired
    private OccupationTableRepository repository;

    @GetMapping("/list")
    public List<OccupationTableEntity> listAll() {
        return repository.findAll();
    }

    @GetMapping("/info/{id}")
    public OccupationTableEntity info(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }
}
