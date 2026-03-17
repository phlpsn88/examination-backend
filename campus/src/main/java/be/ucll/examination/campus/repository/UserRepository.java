package be.ucll.examination.campus.repository;

import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getUsers();

    Optional<User> findUserById(long userId);

    User addUser(User user);

    User updateUser(long userId, User user);

    void removeUser(long userId);

    void removeAllUsers();

    void saveUser(User user);
}
