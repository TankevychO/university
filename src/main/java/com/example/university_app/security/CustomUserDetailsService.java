package com.example.university_app.security;

import java.util.Optional;
import com.example.university_app.model.Student;
import com.example.university_app.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Student> userOptional = studentService.findByEmail(email);
        UserBuilder userBuilder;
        if (userOptional.isPresent()) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(email);
            userBuilder.password(userOptional.get().getPassword());
            userBuilder.roles("USER");
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
