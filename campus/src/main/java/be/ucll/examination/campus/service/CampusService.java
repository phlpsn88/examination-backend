package be.ucll.examination.campus.service;

import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.model.Local;

import java.util.List;
import java.util.Optional;

public interface CampusService {
    List<Campus> allCampuses();

    List<Local> allLocals(String campusName);

    Campus findCampusByName(String name);

    Campus addCampus(Campus campus);

    Campus updateCampus(String name, Campus campus);

    Optional<Local> findLocalByCampusAndName(String campusName, String roomName);

    void removeCampus(String campusName);

    void assignLocalToCampus(Local local, String campusName);

    int countLocalsInCampus(String name);
}
