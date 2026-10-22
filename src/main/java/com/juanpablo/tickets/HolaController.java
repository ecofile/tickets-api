package com.juanpablo.tickets;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @GetMapping("/hola")
    public String hola() {
        return "Mi primera API con Spring Boot - " + LocalDateTime.now();
    }
}