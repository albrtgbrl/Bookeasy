package com.bookeasy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookeasy.entities.Sala;

public interface SalaRepo extends JpaRepository<Sala, Long> {
    
}
