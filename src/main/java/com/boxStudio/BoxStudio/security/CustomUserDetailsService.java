package com.boxStudio.BoxStudio.security;

import com.boxStudio.BoxStudio.model.Trainee;
import com.boxStudio.BoxStudio.repositories.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Trainee trainee = traineeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        return User.withUsername(trainee.getEmail())
                .password(trainee.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(trainee.getRole())))
                .roles(trainee.getRole().replace("ROLE_", ""))
                .build();
    }
}