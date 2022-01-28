package com.example.university_app.security;

import java.util.Optional;
import javax.naming.AuthenticationException;
import com.example.university_app.model.Student;
import com.example.university_app.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class CustomUserDetailsServiceTest {
    private CustomUserDetailsService customUserDetailsService;
    private StudentService studentService = Mockito.mock(StudentService.class);
    private String email;
    private Student bob;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(StudentService.class);
        customUserDetailsService = new CustomUserDetailsService(studentService);
        email = "bob@gmail.com";
        bob = new Student(email, "1234", null);
        Mockito.when(studentService.findByEmail(email)).thenReturn(Optional.of(bob));
    }

    @Test
    void loadUserByUsername_Ok() throws AuthenticationException {
        UserDetails actual = customUserDetailsService.loadUserByUsername(email);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("bob@gmail.com", actual.getUsername());
        Assertions.assertEquals("1234", actual.getPassword());
    }

    @Test
    void loadUserByUsername_userNotFound() throws AuthenticationException {
        try {
            customUserDetailsService.loadUserByUsername("alice2gmail.com");
        } catch (UsernameNotFoundException e) {
            Assertions.assertEquals("User not found!", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive UserNameNotFoundExceptions");
    }
}
