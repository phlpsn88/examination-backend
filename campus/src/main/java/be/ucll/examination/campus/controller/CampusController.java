package be.ucll.examination.campus.controller;

import be.ucll.examination.campus.error.*;
import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.model.Local;
import be.ucll.examination.campus.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campus")
public class CampusController {

    private CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @GetMapping
    public List<Campus> allCampuses() {
        return this.campusService.allCampuses();
    }

    @GetMapping("/{campusName}")   //http://localhost:8080/campus/PROXIMUS
    public Campus getCampusByName(@PathVariable(value = "campusName") String name) {
        return campusService.findCampusByName(name);
    }

    @GetMapping("/{campusName}/amount-locals")   //http://localhost:8080/campus/PROXIMUS/amount-locals
    public String countLocalsInCampus(@PathVariable(value = "campusName") String name) {
        int countLocals = campusService.countLocalsInCampus(name);

        return "{ \"Amount locals in campus " + name + "\": " + countLocals + " }";
    }

    @GetMapping("/{campusName}/rooms")   //http://localhost:8080/campus/PROXIMUS/rooms
    public List<Local> getLocalsByCampusName(@PathVariable String campusName) {
        return campusService.allLocals(campusName);
    }

    @GetMapping("/{campusName}/rooms/{roomsName}")   //http://localhost:8080/campus/PROXIMUS/rooms
    public Optional<Local> getLocalByCampusNameAnsLocalName(@PathVariable String campusName, @PathVariable String roomsName) {
        return campusService.findLocalByCampusAndName(campusName, roomsName);
    }

    @PostMapping //http://localhost:8080/campus
    public Campus addCampus(@RequestBody Campus campus) {
        return campusService.addCampus(campus);
    }

    @PutMapping("/{campusName}")
    public Campus updateCampus(@PathVariable String campusName,@RequestBody Campus campus) {
        return campusService.updateCampus(campusName, campus);
    }

    @DeleteMapping("/{campusName}")
    public void deleteCampus(@PathVariable String campusName) {
        campusService.removeCampus(campusName);
    }

    @PostMapping("/{campusName}/local")
    public Local addLocal(@PathVariable String campusName, @RequestBody Local local) {
        this.campusService.assignLocalToCampus(local, campusName);
        return local;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNeedsANameException.class})
    public FieldMessage handleNameException(CampusNeedsANameException ex) {
        return new FieldMessage("name", "campus requires a name");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNeedsAnAddressException.class})
    public FieldMessage handleNameException(CampusNeedsAnAddressException ex) {
        return new FieldMessage("address", "campus requires an address");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNeedsParkingSpotsException.class})
    public FieldMessage handleNameException(CampusNeedsParkingSpotsException ex) {
        return new FieldMessage("parking spots", "campus requires parking spots");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNeedsToBeUniqueException.class})
    public FieldMessage handleUniqueException(CampusNeedsToBeUniqueException ex) {
        return new FieldMessage("name", "campus name needs to be unique");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LocalNeedsToBeUniqueException.class})
    public FieldMessage handleUniqueLocalException(LocalNeedsToBeUniqueException ex) {
        return new FieldMessage("name", "local name needs to be unique");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CampusNameDoesntExistException.class})
    public FieldMessage handleCampusNotFoundException(CampusNameDoesntExistException ex) {
        return new FieldMessage("name", "Can't find campus with this name");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({LocalNameDoesntExistException.class})
    public FieldMessage handleLocalNotFoundException(LocalNameDoesntExistException ex) {
        return new FieldMessage("name", "Can't find room with this name in this campus");
    }
}
