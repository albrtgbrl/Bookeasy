package com.bookeasy.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookeasy.entities.Sede;
import com.bookeasy.repo.SedeRepo;

@Service
public class SedeServiceImpl implements SedeService{

    private final SedeRepo sedeRepo;

    
    public SedeServiceImpl(SedeRepo sedeRepo) {
        this.sedeRepo = sedeRepo;
    }

    @Override
    public List<Sede> getAllSedi() {
        return sedeRepo.findAll();
    }

}
