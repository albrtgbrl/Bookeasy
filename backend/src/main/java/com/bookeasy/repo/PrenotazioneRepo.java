package com.bookeasy.repo;

import com.bookeasy.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findBySalaIdSala(Long idSala);

    @Query("SELECT COUNT(p) > 0 FROM Prenotazione p " +
           "WHERE p.sala.idSala = :idSala " +
           "AND p.data = :data " +
           "AND p.oraInizio < :oraFine " +
           "AND p.oraFine > :oraInizio")
    boolean isSalaDisponibile(
        @Param("idSala") Long idSala,
        @Param("data") LocalDate data,
        @Param("oraInizio") LocalTime oraInizio,
        @Param("oraFine") LocalTime oraFine
    );
}