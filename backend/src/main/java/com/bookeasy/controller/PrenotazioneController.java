package com.bookeasy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookeasy.entities.Prenotazione;
import com.bookeasy.exceptions.SalaNonDisponibileException;
import com.bookeasy.services.PrenotazioneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/prenotazioni")
@CrossOrigin(origins = "http://localhost:4200") 
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping("/nuova-prenotazione")
    @Operation(summary = "Crea una nuova prenotazione")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Prenotazione effettuata correttamente"),
        @ApiResponse(responseCode = "400", description = "Parametri invalidi"),
        @ApiResponse(responseCode = "409", description = "La sala è già occupata")
    })
    public ResponseEntity<?> addPrenotazione(
            @RequestParam Long idSala,
            @RequestParam Long idUtente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime orarioInizio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime orarioFine) {

        try {
            Prenotazione newPrenotazione = prenotazioneService.addPrenotazione(idSala, idUtente, data, orarioInizio, orarioFine);
            return new ResponseEntity<>(newPrenotazione, HttpStatus.CREATED);
        } catch (SalaNonDisponibileException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/sala/{idSala}")
    @Operation(summary = "Recupera le prenotazioni di una sala", description = "Restituisce la lista di tutte le prenotazioni associate a una specifica sala")
    @ApiResponse(responseCode = "200", description = "Lista recuperata con successo")
    @ApiResponse(responseCode = "404", description = "Sala non trovata")
    public ResponseEntity<List<Prenotazione>> getPrenotazioniBySala(@PathVariable Long idSala) {
        List<Prenotazione> lista = prenotazioneService.getPrenotazioniBySala(idSala);
        return ResponseEntity.ok(lista); 
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una prenotazione", description = "Rimuove permanentemente una prenotazione tramite il suo ID")
    @ApiResponse(responseCode = "204", description = "Prenotazione eliminata con successo (No Content)")
    @ApiResponse(responseCode = "404", description = "Prenotazione non trovata")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build(); 
    }
}