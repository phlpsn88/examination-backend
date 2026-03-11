package be.ucll.examination.campus.repository;

import be.ucll.examination.campus.model.Campus;

import java.util.List;
import java.util.Optional;

public interface CampusRepository {
    List<Campus> getCampuses();

    Optional<Campus> findCampusByName(String name);

    void addCampus(Campus campus);

    Campus updateCampusNameAndAddressAndParkingSpots(String campusName, Campus campus);

    void removeCampus(String campusName);

    void removeAllCampuses();

    void saveCampus(Campus campus);
}
