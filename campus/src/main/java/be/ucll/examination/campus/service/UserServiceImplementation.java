package be.ucll.examination.campus.service;

import be.ucll.examination.campus.error.*;
import be.ucll.examination.campus.model.User;
import be.ucll.examination.campus.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(long userId) {
        return userRepository.findUserById(userId).orElseThrow(
                UserDoesntExistException::new
        );
    }

    @Override
    public User addUser(User user) {
        if (user.getFirstName() == null) {
            throw new UserNeedsAFirstNameException();
        }
        if (user.getLastName() == null) {
            throw new UserNeedsALastNameException();
        }
        if (user.getBirthDate() == null) {
            throw new UserNeedsABirthDateException();
        }
        if (user.getEmail() == null) {
            throw new UserNeedsAnEmailException();
        }

        List<User> allUsers = userRepository.getUsers();

        allUsers.stream()
                .filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .findFirst()
                .ifPresent(user1 -> {
                    throw new UserEmailAlreadyExistException();
                });
        
        userRepository.addUser(user);
        return user;
    }

    @Override
    public User updateUser(long userId, User user) {
        userRepository.findUserById(userId).orElseThrow(
                UserDoesntExistException::new
        );

        return userRepository.updateUser(userId, user);
    }

    @Override
    public void removeUser(long userId) {
        userRepository.removeUser(userId);
    }
}
