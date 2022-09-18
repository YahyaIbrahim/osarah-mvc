package com.wellware.osara.config.security;

import com.wellware.osara.data.entities.User;
import com.wellware.osara.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        log.info("userName:::::" + userName);
        Optional<User> user = userRepository.findByUserName(userName);
        log.info("user.isPresent():::::"+user.isPresent());

        if(user.isEmpty())
            throw  new UsernameNotFoundException("userName " + userName + " Not found");
        else
            return new CustomUserDetails(user.get());

    }
}
