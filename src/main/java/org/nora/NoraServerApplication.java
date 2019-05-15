package org.nora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class NoraServerApplication {

    public static void main(String[] args) throws Exception {

        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(NoraServerApplication.class, args);

        Desktop.getDesktop().browse(new URI("http://localhost:8080"));
    }

}
