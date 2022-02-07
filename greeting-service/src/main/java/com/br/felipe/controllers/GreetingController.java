package com.br.felipe.controllers;

import com.br.felipe.configuration.GreetingConfiguration;
import com.br.felipe.models.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private GreetingConfiguration configuration;

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name ){
        if(name.isEmpty()){
            name = configuration.getDefaultValue();
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
    }



}
