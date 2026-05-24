package com.bookeasy.controller;

import com.bookeasy.entities.Sede;
import com.bookeasy.services.SedeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sedi")
@CrossOrigin(origins = "http://localhost:4200") // Permette ad Angular di comunicare
public class SedeController {

    private final SedeService sedeService;


    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public List<Sede> getAllSedi() {
        return sedeService.getAllSedi();
    }
}