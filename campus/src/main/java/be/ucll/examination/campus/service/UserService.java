package be.ucll.examination.campus.service;

import be.ucll.examination.campus.model.User;

public interface UserService {
    User findUserById(long userId);

    User addUser(User user);

    User updateUser(long userId, User user);

    void removeUser(long userId);
}
