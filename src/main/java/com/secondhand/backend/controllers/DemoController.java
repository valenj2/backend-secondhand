package com.secondhand.backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/publico")
    public String saludopublico(){
        return "Ingreso a un endpoint publico";
    }
    @GetMapping("/saludo")
    public String saludo(){
        return "Hola";
    }

    @GetMapping("/restringido")
    public String saludoprotegido(){
        return "Ingreso a un endpoint restringido";
    }
}
