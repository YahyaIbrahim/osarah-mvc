package com.wellware.osara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
