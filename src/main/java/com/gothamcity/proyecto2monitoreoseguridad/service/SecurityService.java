package com.gothamcity.proyecto2monitoreoseguridad.service;

import com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations.Secured;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Secured(roles = {"ADMIN", "SECURITY"})
    public void performSensitiveOperation() {
        System.out.println("Operación sensible ejecutada.");
    }
}

