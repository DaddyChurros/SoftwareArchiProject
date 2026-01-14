package fr.insa.ms.gestionNourriture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.gestionNourriture.model.PlatEntity;

public interface PlatRepository extends JpaRepository<PlatEntity, Integer> {
}