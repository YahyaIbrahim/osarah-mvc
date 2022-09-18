package com.wellware.osara.controllers;

import com.wellware.osara.serives.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String home(Model model) throws UnsupportedEncodingException {

        userService.sign();

        return "index";

    }

    @RequestMapping("/admin")
    public String admin() {

        return "admin";

    }
}
