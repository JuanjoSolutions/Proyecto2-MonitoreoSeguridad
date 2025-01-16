package com.gothamcity.proyecto2monitoreoseguridad.controller;

import com.gothamcity.proyecto2monitoreoseguridad.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/sensitive-operation")
    public String performSensitiveOperation() {
        securityService.performSensitiveOperation();
        return "Operación sensible realizada exitosamente.";
    }
}

