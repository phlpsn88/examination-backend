package be.ucll.examination.campus.service;

import be.ucll.examination.campus.error.CampusNameDoesntExistException;
import be.ucll.examination.campus.fake.repository.InMemoryCampusRepository;
import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.repository.CampusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampusServiceImplementationTest {

    CampusRepository campusRepository;
    CampusServiceImplementation service;

    @BeforeEach
    public void buildUp() {
        campusRepository = new InMemoryCampusRepository();
        campusRepository.addCampus(new Campus("PROXIMUS", "Geldenaaksebaan 335, 3001 Leuven", 450));
        campusRepository.addCampus(new Campus("DIEPENBEEK", "Gebouw B, Agoralaan, 3590 Diepenbeek", 150));
        campusRepository.addCampus(new Campus("HASSELT", "Oude Luikerbaan 79, 3500 Hasselt", 220));
        service = new CampusServiceImplementation(campusRepository);
    }

    @Test
    public void givenFindCampusByName_whenCampusExists_thenItIsReturned() {
        Campus foundCampus = service.findCampusByName("PROXIMUS");
        Assertions.assertEquals("PROXIMUS", foundCampus.getName());
    }

    @Test
    public void givenFindCampusByName_whenCampusDoesntExist_thenExceptionIsThrown() {
        Assertions.assertThrows(CampusNameDoesntExistException.class, () -> service.findCampusByName("DIEST"));
    }
}
