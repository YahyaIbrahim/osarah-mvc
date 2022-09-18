package com.wellware.osara.controllers;

import com.wellware.osara.data.objects.Cert;
import com.wellware.osara.serives.UserService;
import com.wellware.osara.utils.ReportGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportGenerator generator;


    @RequestMapping("/")
    public void home(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("report-name", "RawData_Report");
        attributes.put("name", "يحيي ابراهيم عبد العليم");
        int duration = 4;
        int days = 1;
        String durationString = "بواقع " + duration + " ساعة تدريبية خلال " + days + " أيام";
        attributes.put("courseName", "Docker");
        attributes.put("duration", durationString);
        attributes.put("currentDay", "18-18/9/2022");
        Cert cert = new Cert();
        cert.setName("Yehia Ibrahim");
        cert.setCourseName("Docker");
        cert.setDuration(durationString);

        cert.setCurrentDay("18-18/9/2022");

//        session.setAttribute("report-name", "RawData_Report");
//        session.setAttribute("name", "يحيي ابراهيم عبد العليم");
//        session.setAttribute("courseName", "Docker");
//        session.setAttribute("duration", 4);
//        session.setAttribute("days", 1);
//        session.setAttribute("currentDay", "18-18/9/2022");

       // userService.retrieveRawData("يحيي ابراهيم عبد العليم","Docker",4,1,"18-18/9/2022");
        generator.generateReport(attributes, request, response);


    }

    @RequestMapping("/admin")
    public String admin() {

        return "admin";

    }
}
