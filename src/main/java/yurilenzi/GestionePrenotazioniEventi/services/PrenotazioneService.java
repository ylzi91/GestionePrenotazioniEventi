package yurilenzi.GestionePrenotazioniEventi.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurilenzi.GestionePrenotazioniEventi.entities.Evento;
import yurilenzi.GestionePrenotazioniEventi.entities.Prenotazione;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.FinishPlaceException;
import yurilenzi.GestionePrenotazioniEventi.exceptions.NotFoundException;
import yurilenzi.GestionePrenotazioniEventi.exceptions.OrganaizerException;
import yurilenzi.GestionePrenotazioniEventi.exceptions.UtenteGiaPrenotatoException;
import yurilenzi.GestionePrenotazioniEventi.payloads.PrenotazioneResponseDTO;
import yurilenzi.GestionePrenotazioniEventi.repositories.PrenotazioneRepository;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    EventoService eventoService;

    public PrenotazioneResponseDTO nuovaPrenotazione(Long idEvento, Utente utentePrenotante){
        Evento foundEvento = eventoService.findById(idEvento);
        Long numeroPrenotazioni = prenotazioneRepository.contaPrenotazioniPerEvento(foundEvento);
        if(numeroPrenotazioni == 0){
            Prenotazione newPrenotazione = prenotazioneRepository.save(new Prenotazione(foundEvento, utentePrenotante));
            Long prenotazioniRimanenti = foundEvento.getMaxPartecipanti() - numeroPrenotazioni;
            return new PrenotazioneResponseDTO(newPrenotazione, prenotazioniRimanenti);
        }
        else {
            Long prenotazioniRimanenti = foundEvento.getMaxPartecipanti() - numeroPrenotazioni;
            if(prenotazioniRimanenti == 0)
                throw new FinishPlaceException();
            if(prenotazioneRepository.vediSeUtenteGiaPrenotato(foundEvento, utentePrenotante).isPresent())
                throw new UtenteGiaPrenotatoException(utentePrenotante.getUsername());
            Prenotazione newPrenotazione = prenotazioneRepository.save(new Prenotazione(foundEvento, utentePrenotante));
            numeroPrenotazioni = prenotazioneRepository.contaPrenotazioniPerEvento(foundEvento);
            prenotazioniRimanenti = foundEvento.getMaxPartecipanti() - numeroPrenotazioni;
            return new PrenotazioneResponseDTO(newPrenotazione, prenotazioniRimanenti);
        }

    }

    public List<Prenotazione> vediTutteLePrenotazioni(){
       return prenotazioneRepository.findAll();
    }

    public List<Prenotazione> vediPrenotazioniUtente(Utente utente){
        return prenotazioneRepository.findByUtente(utente);
    }
    public Prenotazione findById(Long idPrenotazione){
        Prenotazione found = null;
        return prenotazioneRepository.findById(idPrenotazione).orElseThrow(() -> new NotFoundException(idPrenotazione));
    }

    public void deletePrenotazione(Utente utenteCorrente, Long idPrenotazione){
        Prenotazione found = findById(idPrenotazione);
        if(utenteCorrente.getUsername().equals(found.getUtente().getUsername())){
            prenotazioneRepository.delete(found);
        }
        else{
            throw new OrganaizerException(utenteCorrente.getUsername(), found.getUtente().getUsername());
        }
    }

    public void deletePrenotazioneMaster(Long idPrenotazione){
        Prenotazione found = findById(idPrenotazione);
        prenotazioneRepository.delete(found);
    }
}
