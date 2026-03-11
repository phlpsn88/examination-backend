package be.ucll.examination.campus.service;

import be.ucll.examination.campus.model.Campus;

import java.util.List;

public interface CampusService {
    List<Campus> allCampuses();

    Campus findCampusByName(String name);

    Campus addCampus(Campus campus);

    Campus updateCampus(String name, Campus campus);

    void removeCampus(String campusName);
}
