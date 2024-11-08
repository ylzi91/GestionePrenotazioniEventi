package yurilenzi.GestionePrenotazioniEventi.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yurilenzi.GestionePrenotazioniEventi.entities.Evento;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.BadRequestException;
import yurilenzi.GestionePrenotazioniEventi.payloads.NewEventoDTO;
import yurilenzi.GestionePrenotazioniEventi.services.EventoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    EventoService eventoService;

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento aggiungiEvento(@AuthenticationPrincipal Utente currentOrganizzatore, @RequestBody @Validated NewEventoDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(message);
        }
        return eventoService.creaEvento(currentOrganizzatore, body);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Evento> vediEventi(){
        return eventoService.vediTuttiGliEventi();
    }

    @PutMapping("/{idEvento}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento modficaEvento(@PathVariable Long idEvento, @RequestBody NewEventoDTO body, @AuthenticationPrincipal Utente currentOrganizzatore){
        return  eventoService.modificaEvento(idEvento, body, currentOrganizzatore);
    }

    @GetMapping("/miei")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Evento> vediMieiEventi(@AuthenticationPrincipal Utente currentOrganizzatore){
        return eventoService.vediMieiEventi(currentOrganizzatore);
    }



}
