package com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Punto de corte para todos los métodos en el paquete de servicios
    @Pointcut("execution(* com.gothamcity.proyecto2monitoreoseguridad.service.*.*(..))")
    public void serviceMethods() {}

    // Antes de la ejecución del método
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Iniciando método: " + joinPoint.getSignature().getName());
    }

    // Después de la ejecución del método
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Finalizando método: " + joinPoint.getSignature().getName());
    }

    // Alrededor de la ejecución del método
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Ejecutando método: " + joinPoint.getSignature().getName());
        try {
            Object result = joinPoint.proceed();
            logger.info("Método ejecutado exitosamente: " + joinPoint.getSignature().getName());
            return result;
        } catch (Exception e) {
            logger.error("Error en método: " + joinPoint.getSignature().getName(), e);
            throw e;
        }
    }
}

