package fr.insa.ms.occupationTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.occupationTable.model.OccupationTableEntity;

public interface OccupationTableRepository extends JpaRepository<OccupationTableEntity, Integer> { }
