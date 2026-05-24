package com.bookeasy.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.bookeasy.entities.Prenotazione;
import com.bookeasy.exceptions.SalaNonDisponibileException;

public interface PrenotazioneService {

    List<Prenotazione> getPrenotazioniBySala(Long idSala);
    Prenotazione addPrenotazione(Long idSala, Long idUtente, LocalDate data, LocalTime orarioInizio, LocalTime orarioFine) throws SalaNonDisponibileException;
    void deletePrenotazione(Long idPrenotazione);
    boolean checkSalaAvailability(Long idSala, LocalDate data, LocalTime oraInizio, LocalTime oraFine) throws SalaNonDisponibileException;
}
