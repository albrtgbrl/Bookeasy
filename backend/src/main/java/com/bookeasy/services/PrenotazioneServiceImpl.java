package com.bookeasy.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookeasy.entities.Prenotazione;
import com.bookeasy.entities.Sala;
import com.bookeasy.entities.Utente;
import com.bookeasy.exceptions.SalaNonDisponibileException;
import com.bookeasy.repo.PrenotazioneRepo;
import com.bookeasy.repo.SalaRepo;
import com.bookeasy.repo.UtenteRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

    private final PrenotazioneRepo prenotazioneRepo;
    private final SalaRepo salaRepo;
    private final UtenteRepo utenteRepo;

    public PrenotazioneServiceImpl(PrenotazioneRepo prenotazioneRepo, SalaRepo salaRepo, UtenteRepo utenteRepo) {
        this.prenotazioneRepo = prenotazioneRepo;
        this.salaRepo = salaRepo;
        this.utenteRepo = utenteRepo;
    }

    @Override
    public List<Prenotazione> getPrenotazioniBySala(Long idSala) {

       if(idSala == null){
            throw new IllegalArgumentException("L'id della sala non può essere null");
       }

       if(!salaRepo.existsById(idSala)){
            throw new EntityNotFoundException("Non esiste una sala con questo id");
       }

       return prenotazioneRepo.findBySalaIdSala(idSala);
    }

    @Override
    public boolean checkSalaAvailability(Long idSala, LocalDate data, LocalTime oraInizio, LocalTime oraFine) throws SalaNonDisponibileException {

        if(idSala == null || data == null || oraInizio == null || oraFine == null){
            throw new IllegalArgumentException("I parametri non possono essere null");
        }

        if(!salaRepo.existsById(idSala)){
            throw new EntityNotFoundException("Non esiste una sala con questo id");
       }

        if(!prenotazioneRepo.isSalaDisponibile(idSala, data, oraInizio, oraFine)){
            throw new SalaNonDisponibileException("La sala non è disponibile per questo orario", null);
        }
        return true;
    }

    @Override
    public void deletePrenotazione(Long idPrenotazione) {
       if(idPrenotazione == null) {
            throw new IllegalArgumentException("L'id della prenotazione non può essere null");
       }

       prenotazioneRepo.deleteById(idPrenotazione);
    }

    @Override
    public Prenotazione addPrenotazione(Long idSala, Long idUtente, LocalDate data, LocalTime orarioInizio, LocalTime orarioFine) throws SalaNonDisponibileException {
        
       if(idSala == null || idUtente == null || data == null || orarioInizio == null || orarioFine == null){
            throw new IllegalArgumentException("I parametri non possono essere null");
       }

       if (!orarioFine.isAfter(orarioInizio)) {
            throw new IllegalArgumentException("L'orario di fine deve essere successivo a quello di inizio");
       }

       Sala sala = salaRepo.findById(idSala)
                    .orElseThrow(() -> new EntityNotFoundException("Non esiste nessuna sala con questo id"));
       
       Utente utente = utenteRepo.findById(idUtente)
                    .orElseThrow(() -> new EntityNotFoundException("Non esiste nessun utente con questo id"));

       if (!prenotazioneRepo.isSalaDisponibile(idSala, data, orarioInizio, orarioFine)) {
            throw new SalaNonDisponibileException("La sala non è disponibile nella fascia oraria richiesta.");
       }

       Prenotazione newPrenotazione = new Prenotazione();
       newPrenotazione.setSala(sala);
       newPrenotazione.setUtente(utente);
       newPrenotazione.setData(data);
       newPrenotazione.setOraInizio(orarioInizio);
       newPrenotazione.setOraFine(orarioFine);

       return prenotazioneRepo.save(newPrenotazione);
    }
}