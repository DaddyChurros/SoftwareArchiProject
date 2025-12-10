package fr.insa.ms.gestTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.gestTable.model.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Integer> {

}
