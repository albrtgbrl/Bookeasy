package com.bookeasy.services;

import java.util.List;

import com.bookeasy.dto.SalaDTO;
import com.bookeasy.entities.Sala;

public interface SalaService {

    List<Sala> getAllSale();
    Sala createSala(SalaDTO salaDTO);
}
