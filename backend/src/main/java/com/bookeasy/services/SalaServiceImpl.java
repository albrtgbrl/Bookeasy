package com.bookeasy.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookeasy.dto.SalaDTO;
import com.bookeasy.entities.Sala;
import com.bookeasy.entities.Sede;
import com.bookeasy.repo.SalaRepo;
import com.bookeasy.repo.SedeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SalaServiceImpl implements SalaService {

    private final SedeRepo sedeRepo;
    private final SalaRepo salaRepo;

    public SalaServiceImpl(SedeRepo sedeRepo, SalaRepo salaRepo){
        this.sedeRepo = sedeRepo;
        this.salaRepo = salaRepo;
    }

    @Override
    public Sala createSala(SalaDTO dto) {
    
        if (dto == null) {
            throw new IllegalArgumentException("I dati della sala sono obbligatori");
        }
        if (dto.getIdSede() == null || dto.getNome() == null || dto.getCapienza() == null) {
            throw new IllegalArgumentException("Tutti i campi (idSede, nome, capienza) sono obbligatori");
        }
        if (dto.getCapienza() <= 0) {
            throw new IllegalArgumentException("La capienza deve essere maggiore di zero");
        }

        Sede sede = sedeRepo.findById(dto.getIdSede())
                .orElseThrow(() -> new EntityNotFoundException("Sede non trovata"));

        Sala nuovaSala = new Sala();
        nuovaSala.setNome(dto.getNome());
        nuovaSala.setCapienza(dto.getCapienza());
        nuovaSala.setSede(sede);

        return salaRepo.save(nuovaSala);
    }

    @Override
    public List<Sala> getAllSale() {
        return salaRepo.findAll();
    }
}