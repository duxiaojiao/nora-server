package org.nora.modules.system.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value="/")
    public String say(){

        return "Hello World";
    }
}
