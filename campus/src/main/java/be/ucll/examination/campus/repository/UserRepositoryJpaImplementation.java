package be.ucll.examination.campus.repository;

import be.ucll.examination.campus.model.User;
import be.ucll.examination.campus.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UserRepositoryJpaImplementation implements UserRepository {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryJpaImplementation(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> getUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public User addUser(User user) {
        userJpaRepository.save(user);

        return user;
    }

    @Override
    public User updateUser(long userId, User user) {
        findUserById(userId)
                .ifPresent(user1 -> {
                    user1.updateUser(user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getEmail());
                    userJpaRepository.save(user1);
                });

        return user;
    }

    @Override
    public void removeUser(long userId) {
        findUserById(userId)
                .ifPresent(user1 -> {
                    userJpaRepository.delete(user1);
                });
    }

    @Override
    public void removeAllUsers() {
        userJpaRepository.deleteAll();
    }

    @Override
    public void saveUser(User user) {
        userJpaRepository.save(user);
    }
}
