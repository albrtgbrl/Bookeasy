package com.bookeasy.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookeasy.dto.SalaDTO;
import com.bookeasy.entities.Prenotazione;
import com.bookeasy.entities.Sala;
import com.bookeasy.services.PrenotazioneService;
import com.bookeasy.services.SalaService;
import com.bookeasy.exceptions.SalaNonDisponibileException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200") 
public class SalaController {

    private final SalaService salaService;
    private final PrenotazioneService prenotazioneService;

    public SalaController(SalaService salaService, PrenotazioneService prenotazioneService) {
        this.salaService = salaService;
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping("/crea")
    @Operation(summary = "Crea una nuova sala", description = "Inserisce una nuova sala nel sistema associandola a una sede esistente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sala creata con successo"),
        @ApiResponse(responseCode = "400", description = "Dati in ingresso non validi"),
        @ApiResponse(responseCode = "404", description = "Sede specificata non trovata nel database")
    })
    public ResponseEntity<Sala> createSala(@RequestBody SalaDTO salaDTO) {
        Sala nuovaSala = salaService.createSala(salaDTO);
        return new ResponseEntity<>(nuovaSala, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Recupero elenco sale", description = "Ritorna la lista di tutte le sale presenti nel sistema")
    @ApiResponse(responseCode = "200", description = "Elenco recuperato con successo")
    public ResponseEntity<List<Sala>> getAllSale() {
        List<Sala> sale = salaService.getAllSale();
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/{idSala}/prenotazioni")
    @Operation(summary = "Recupero prenotazioni di una sala", description = "Ritorna tutte le prenotazioni associate a una specifica sala")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prenotazioni recuperate con successo"),
        @ApiResponse(responseCode = "404", description = "Sala specificata non trovata")
    })
    public ResponseEntity<List<Prenotazione>> getPrenotazioniBySala(@PathVariable Long idSala) {
        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniBySala(idSala);
        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }

    @GetMapping("/{idSala}/disponibilita")
    @Operation(summary = "Verifica disponibilità di una sala", description = "Verifica se una sala è libera in una specifica data e fascia oraria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verifica effettuata con successo"),
        @ApiResponse(responseCode = "400", description = "Parametri di input non validi"),
        @ApiResponse(responseCode = "404", description = "Sala specificata non trovata")
    })
    public ResponseEntity<Boolean> verificaDisponibilita(
            @PathVariable Long idSala,
            @RequestParam String data,
            @RequestParam String oraInizio,
            @RequestParam String oraFine) {
        
        LocalDate dataParsed = LocalDate.parse(data);
        LocalTime inizioParsed = LocalTime.parse(oraInizio);
        LocalTime fineParsed = LocalTime.parse(oraFine);
        
        try {
            boolean disponibile = prenotazioneService.checkSalaAvailability(idSala, dataParsed, inizioParsed, fineParsed);
            return new ResponseEntity<>(disponibile, HttpStatus.OK);
        } catch (SalaNonDisponibileException e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}