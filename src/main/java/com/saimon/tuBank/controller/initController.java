package com.saimon.tuBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class initController {

    @GetMapping
    public String teste() {
        return "Teste";
    }
}
