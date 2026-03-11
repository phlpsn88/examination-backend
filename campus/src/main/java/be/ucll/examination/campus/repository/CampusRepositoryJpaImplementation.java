package be.ucll.examination.campus.repository;

import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.repository.jpa.CampusJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class CampusRepositoryJpaImplementation implements CampusRepository {

    private CampusJpaRepository campusJpaRepository;

    @Autowired
    public CampusRepositoryJpaImplementation(CampusJpaRepository campusJpaRepository) {
        this.campusJpaRepository = campusJpaRepository;
    }

    @Override
    public List<Campus> getCampuses() {
        return campusJpaRepository.findAll();
    }

    @Override
    public Optional<Campus> findCampusByName(String name) {
        return campusJpaRepository.findByName(name);
    }

    @Override
    public void addCampus(Campus campus) {
        campusJpaRepository.save(campus);
    }

    @Override
    public Campus updateCampusNameAndAddressAndParkingSpots(String campusName, Campus campus) {
        findCampusByName(campusName)
                .ifPresent(campus1 -> {
                    campus1.updateNameAddressAndParkingSpots(campus.getName(), campus.getAddress(), campus.getParkingSpots());
                    campusJpaRepository.save(campus1);
                });

        return campus;
    }

    @Override
    public void removeCampus(String campusName) {
        findCampusByName(campusName)
                .ifPresent(campus1 -> {
                    campusJpaRepository.delete(campus1);
                });

    }

    @Override
    public void removeAllCampuses() {
        campusJpaRepository.deleteAll();
    }

    @Override
    public void saveCampus(Campus campus) {
        campusJpaRepository.save(campus);
    }
}
