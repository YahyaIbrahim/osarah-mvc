package com.wellware.osara.serives;

import com.wellware.osara.config.security.CustomUserDetails;
import com.wellware.osara.data.entities.User;
import com.wellware.osara.data.objects.Cert;
import com.wellware.osara.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Cert retrieveRawData(String name,String courseName,String duration,String currentDay){

        Cert cert = new Cert();
        cert.setName(name);
        cert.setCourseName(courseName);
        cert.setDuration(duration);
        cert.setCurrentDay(currentDay);
        return cert;
    }


}
