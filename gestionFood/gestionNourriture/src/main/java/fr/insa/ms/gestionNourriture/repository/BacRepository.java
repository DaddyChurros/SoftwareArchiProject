package fr.insa.ms.gestionNourriture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.gestionNourriture.model.BacEntity;

public interface BacRepository extends JpaRepository<BacEntity, Integer> {
}
