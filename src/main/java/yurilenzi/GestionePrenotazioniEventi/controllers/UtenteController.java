package yurilenzi.GestionePrenotazioniEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.services.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    UtenteService utenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Utente> vediTutti(){
        return utenteService.vediTutti();
    }
}
