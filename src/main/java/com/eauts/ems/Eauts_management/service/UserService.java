package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.model.Role;
import com.eauts.ems.Eauts_management.model.User;
import com.eauts.ems.Eauts_management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        // Nếu người dùng không gửi role, mặc định là USER
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }

        repo.save(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User authenticatedUser = repo.findByUsername(user.getUsername());
            if (authenticatedUser != null) {
                return jwtService.generateToken(authenticatedUser.getId(),authenticatedUser.getUsername(), authenticatedUser.getRole().name());
            }
        }
        return "fail";
    }

}
