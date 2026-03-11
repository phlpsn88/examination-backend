package be.ucll.examination.campus.repository.jpa;

import be.ucll.examination.campus.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampusJpaRepository extends JpaRepository<Campus, Long> {
    Optional<Campus> findByName(String name);
}
