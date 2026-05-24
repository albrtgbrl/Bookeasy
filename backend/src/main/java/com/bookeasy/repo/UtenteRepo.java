package com.bookeasy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookeasy.entities.Utente;

public interface UtenteRepo extends JpaRepository<Utente, Long> {

}
