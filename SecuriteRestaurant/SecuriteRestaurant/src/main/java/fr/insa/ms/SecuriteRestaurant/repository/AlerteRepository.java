package fr.insa.ms.SecuriteRestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.SecuriteRestaurant.model.AlerteEntity;

public interface AlerteRepository extends JpaRepository<AlerteEntity, Long> {
}
