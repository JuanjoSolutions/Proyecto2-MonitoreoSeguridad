package com.gothamcity.proyecto2monitoreoseguridad.controller;

import com.gothamcity.proyecto2monitoreoseguridad.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/process")
    public String processData(@RequestBody List<String> dataList) {
        dataService.processDataAsync(dataList);
        return "Procesamiento de datos iniciado.";
    }
}

