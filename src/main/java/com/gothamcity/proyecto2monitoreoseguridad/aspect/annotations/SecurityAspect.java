package com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations;

import com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations.Secured;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class SecurityAspect {

    // Punto de corte para métodos que requieren autenticación
    @Pointcut("@annotation(com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations.Secured)")
    public void securedMethods() {}

    @Before("securedMethods()")
    public void checkSecurity(JoinPoint joinPoint) throws SecurityException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new SecurityException("Acceso denegado: Usuario no autenticado.");
        }

        // Obtener roles requeridos desde la anotación
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Secured secured = method.getAnnotation(Secured.class);
        String[] requiredRoles = secured.roles();

        // Verificar si el usuario tiene alguno de los roles requeridos
        boolean hasRole = Arrays.stream(requiredRoles)
                .anyMatch(role -> auth.getAuthorities().stream()
                        .anyMatch(authRole -> authRole.getAuthority().equals("ROLE_" + role)));
        if (!hasRole) {
            throw new SecurityException("Acceso denegado: Usuario no tiene los roles requeridos.");
        }
    }
}

