package be.ucll.examination.campus.service;

import be.ucll.examination.campus.error.*;
import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.model.Local;
import be.ucll.examination.campus.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusServiceImplementation implements CampusService {

    private final CampusRepository campusRepository;

    @Autowired
    public CampusServiceImplementation(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public List<Campus> allCampuses() {
        return campusRepository.getCampuses();
    }

    @Override
    public Campus findCampusByName(String name) {
        return campusRepository.findCampusByName(name).orElseThrow(
                CampusNameDoesntExistException::new
        );
    }

    @Override
    public Campus addCampus(Campus campus) {
        campusRepository.findCampusByName(campus.getName()).ifPresent(
                (c) -> {
                    throw new CampusNeedsToBeUniqueException();
                }
        );

        if (campus.getName() == null) {
            throw new CampusNeedsANameException();
        }
        if (campus.getAddress() == null) {
            throw new CampusNeedsAnAddressException();
        }
        if (campus.getParkingSpots() == null) {
            throw new CampusNeedsParkingSpotsException();
        }

        campusRepository.addCampus(campus);
        return campus;
    }

    @Override
    public Campus updateCampus(String campusName, Campus campus) {
        campusRepository.findCampusByName(campusName).orElseThrow(
                CampusNameDoesntExistException::new
        );

        return campusRepository.updateCampusNameAndAddressAndParkingSpots(campusName, campus);
    }

    @Override
    public void removeCampus(String campusName) {
        campusRepository.removeCampus(campusName);
    }

    @Override
    public void assignLocalToCampus(Local newLocal, String campusName) {
        Campus campus = campusRepository.findCampusByName(campusName).orElseThrow(
                CampusNameDoesntExistException::new
        );

        // Check of de naam van de local al bestaat in deze campus
        List<Local> campusLocals = campus.getLocals();
        for (Local local : campusLocals) {
            if (local.getName().equals(newLocal.getName())) {
                throw new LocalNeedsToBeUniqueException();
            }
        }

        campus.addLocal(newLocal);

        campusRepository.saveCampus(campus);
    }
}
