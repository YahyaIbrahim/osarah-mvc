package com.wellware.osara.controllers;

import com.wellware.osara.data.entities.User;
import com.wellware.osara.data.objects.Login;
import com.wellware.osara.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;

//    @RequestMapping("/login")
//    public String login() {
//    return "login";
//    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null && !error.isEmpty()) {
            log.error(error);
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        }
        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
