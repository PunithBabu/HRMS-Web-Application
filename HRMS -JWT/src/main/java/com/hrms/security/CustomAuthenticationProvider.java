/**
 * Custom authentication provider class for authentication in the HRMS (Human Resource Management System) security.
 * Extends the DaoAuthenticationProvider class provided by Spring Security.
 */
package com.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hrms.entities.Employee;
import com.hrms.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private EmployeeRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Initializes the authentication provider by setting the user details service.
     */
    @PostConstruct
    public void init() {
        setUserDetailsService(userDetailsService);
    }

    @Override
    @SuppressWarnings("unused")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Check if the user exists in the database
        Employee user = userRepository.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("User not found");
        }

        // Perform additional checks on the user

        // Use the parent class's authenticate method to perform password validation
        return super.authenticate(authentication);
    }
}
