package be.ucll.examination.campus.service;

import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.model.Local;

import java.util.List;

public interface CampusService {
    List<Campus> allCampuses();

    Campus findCampusByName(String name);

    Campus addCampus(Campus campus);

    Campus updateCampus(String name, Campus campus);

    void removeCampus(String campusName);

    void assignLocalToCampus(Local local, String campusName);
}
