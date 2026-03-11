package be.ucll.examination.campus.controller;

import be.ucll.examination.campus.error.*;
import be.ucll.examination.campus.model.User;
import be.ucll.examination.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user-id}")   //http://localhost:8080/user/1
    public User getUserById(@PathVariable(value = "user-id") long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping //http://localhost:8080/user
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserEmailAlreadyExistException.class})
    public FieldMessage UserEmailAlreadyExists(UserEmailAlreadyExistException ex) {
        return new FieldMessage("email", "User with this email already exists");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNeedsAnEmailException.class})
    public FieldMessage UserNeedsAnEmail(UserNeedsAnEmailException ex) {
        return new FieldMessage("email", "User's email can not be empty");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNeedsABirthDateException.class})
    public FieldMessage UserNeedsABirthDate(UserNeedsABirthDateException ex) {
        return new FieldMessage("birth date", "User's birth date can not be empty");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNeedsAFirstNameException.class})
    public FieldMessage UserNeedsAFirstName(UserNeedsAFirstNameException ex) {
        return new FieldMessage("first name", "User's first name can not be empty");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNeedsALastNameException.class})
    public FieldMessage UserNeedsALastName(UserNeedsALastNameException ex) {
        return new FieldMessage("last name", "User's last name can not be empty");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserDoesntExistException.class})
    public FieldMessage handleUserNotFoundException(UserDoesntExistException ex) {
        return new FieldMessage("id", "Can't find user with this id");
    }
}
