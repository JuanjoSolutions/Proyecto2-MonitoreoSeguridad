package com.gothamcity.proyecto2monitoreoseguridad.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DataService {

    @Async
    public CompletableFuture<Void> processDataAsync(List<String> dataList) {
        // Procesamiento de datos en segundo plano
        dataList.forEach(data -> {
            // Simulación de procesamiento
            System.out.println("Procesando dato: " + data);
        });
        return CompletableFuture.completedFuture(null);
    }
}

