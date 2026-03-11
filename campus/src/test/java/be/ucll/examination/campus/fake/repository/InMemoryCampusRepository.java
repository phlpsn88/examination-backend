package be.ucll.examination.campus.fake.repository;

import be.ucll.examination.campus.error.CampusNameDoesntExistException;
import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.repository.CampusRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCampusRepository implements CampusRepository {

    private List<Campus> campuses;

    public InMemoryCampusRepository() {
        campuses = new ArrayList<>();
    }

    @Override
    public List<Campus> getCampuses() {
        return campuses;
    }

    @Override
    public Optional<Campus> findCampusByName(String name) {
        return this.campuses.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public void addCampus(Campus campus) {
        campuses.add(campus);
    }

    @Override
    public Campus updateCampusNameAndAddressAndParkingSpots(String campusName, Campus campus) {
        Campus originalCampus = this.findCampusByName(campusName).orElseThrow();
        originalCampus.updateNameAddressAndParkingSpots(campus.getName(), campus.getAddress(), campus.getParkingSpots());
        return originalCampus;
    }

    @Override
    public void removeCampus(String campusName) {
        Campus campus = this.findCampusByName(campusName).orElseThrow(
                CampusNameDoesntExistException::new
        );
        this.campuses.remove(campus);
    }

    @Override
    public void removeAllCampuses() {
        this.campuses.clear();
    }

    @Override
    public void saveCampus(Campus campus) {

    }
}
