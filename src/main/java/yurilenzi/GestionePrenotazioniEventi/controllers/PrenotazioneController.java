package yurilenzi.GestionePrenotazioniEventi.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import yurilenzi.GestionePrenotazioniEventi.entities.Prenotazione;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.payloads.PrenotazioneResponseDTO;
import yurilenzi.GestionePrenotazioniEventi.services.PrenotazioneService;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @PostMapping("/{idEvento}")
    public PrenotazioneResponseDTO creaNuovaPrenotazione(@PathVariable Long idEvento, @AuthenticationPrincipal Utente utentePrenotante){
        return prenotazioneService.nuovaPrenotazione(idEvento, utentePrenotante);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Prenotazione> vediTutteLePrenotazioni(){
        return prenotazioneService.vediTutteLePrenotazioni();
    }

    @GetMapping("/mie")
    public List<Prenotazione> vediPrenotazioneUtente(@AuthenticationPrincipal Utente currentUtente){
        return prenotazioneService.vediPrenotazioniUtente(currentUtente);
    }

    @DeleteMapping("/mie/{idPrenotazione}")
    public void vediPrenotazioneUtente(@AuthenticationPrincipal Utente currentUtente, Long idPrenotazione){
        prenotazioneService.deletePrenotazione(currentUtente, idPrenotazione);
    }

    @DeleteMapping("/{idPrenotazione}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public void EliminaPrenotazione(@PathVariable Long idPrenotazione){
        prenotazioneService.deletePrenotazioneMaster(idPrenotazione);
    }
}
