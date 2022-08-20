package com.wellware.osara.controllers;

import com.wellware.osara.data.entities.User;
import com.wellware.osara.data.objects.Login;
import com.wellware.osara.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody Login login) {
        Optional<User> user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
    return user.isPresent();
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
