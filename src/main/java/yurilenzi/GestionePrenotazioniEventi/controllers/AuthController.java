package yurilenzi.GestionePrenotazioniEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.payloads.NewUtenteDTO;
import yurilenzi.GestionePrenotazioniEventi.services.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UtenteService utenteService;
    @PostMapping("/register")

    public Utente registraUtente(@RequestBody NewUtenteDTO body){
        return utenteService.saveUtente(body);
    }
}
