package yurilenzi.GestionePrenotazioniEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.BadRequestException;
import yurilenzi.GestionePrenotazioniEventi.payloads.LoginDTO;
import yurilenzi.GestionePrenotazioniEventi.payloads.LoginResponseDTO;
import yurilenzi.GestionePrenotazioniEventi.payloads.NewUtenteDTO;
import yurilenzi.GestionePrenotazioniEventi.services.AuthService;
import yurilenzi.GestionePrenotazioniEventi.services.UtenteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UtenteService utenteService;

    @PostMapping("/login")
    public LoginResponseDTO loginUtente(@RequestBody LoginDTO body){
        return new LoginResponseDTO(authService.checkCredentialAndResponseToken(body));
    }

    @PostMapping("/register")
    public Utente registraUtente(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(message);
        }
        return utenteService.saveUtente(body);
    }
}
