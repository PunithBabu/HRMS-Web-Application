package com.hrms.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrms.entities.Employee;
import com.hrms.repository.EmployeeRepository;

/**
 * Implementation of the UserDetailsService interface for loading user details.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository userRepository;

    /**
     * Loads the user details by the specified email.
     *
     * @param email The email of the user.
     * @return The UserDetails object containing the user details.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                getAuthority(user));
    }

    /**
     * Retrieves the user's authority (role).
     *
     * @param user The user.
     * @return The list of GrantedAuthority representing the user's role.
     */
    private List<GrantedAuthority> getAuthority(Employee user) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }
}
