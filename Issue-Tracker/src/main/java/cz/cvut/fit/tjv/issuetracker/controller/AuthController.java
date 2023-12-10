package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authenticateuser")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody UserCreateDTO user) {
        Optional<User> userInDatabase = userService.findByUsername(user.getUsername());

        if (userInDatabase.isEmpty() || !passwordEncoder.matches(user.getPassword(), userInDatabase.get().getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        else
            return ResponseEntity.ok("Authentication successful");
    }
}