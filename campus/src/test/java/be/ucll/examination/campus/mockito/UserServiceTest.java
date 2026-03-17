package be.ucll.examination.campus.mockito;

import be.ucll.examination.campus.model.User;
import be.ucll.examination.campus.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void AddUserMockitoTest() {
        UserRepository fakeRepository = mock(UserRepository.class);

        User newUser = new User("Wout", "Philipsen", LocalDate.parse("1993-10-15"), "philipsenwout@outlook.com");

        // Test add user with when -> thenReturn
        when(fakeRepository.addUser(newUser)).thenReturn(newUser);

        // Test addUser with assertEquals
        assertEquals(newUser, fakeRepository.addUser(newUser));

        // Test addUser with verify
        verify(fakeRepository).addUser(newUser);
    }
}
