package yurilenzi.GestionePrenotazioniEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurilenzi.GestionePrenotazioniEventi.entities.Evento;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.NotFoundException;
import yurilenzi.GestionePrenotazioniEventi.exceptions.OrganaizerException;
import yurilenzi.GestionePrenotazioniEventi.payloads.NewEventoDTO;
import yurilenzi.GestionePrenotazioniEventi.repositories.EventoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    UtenteService utenteService;

    public Evento creaEvento(Utente currentOrg, NewEventoDTO body){
        return eventoRepository.save(
                new Evento(body.titolo(),
                        body.descrizione(),
                        body.luogo(),
                        body.maxPartecipanti(),
                        LocalDate.parse(body.dataEvento()),
                        currentOrg)
        );
    }

    public List<Evento> vediTuttiGliEventi(){
        return eventoRepository.findAll();
    }

    public Evento findById(Long id){
        Evento found = null;
        found = eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return found;
    }

    public Evento modificaEvento(Long id, NewEventoDTO body, Utente currentUtente){
        Evento modEvento = findById(id);
        if(!currentUtente.getUsername().equals(modEvento.getUtente().getUsername())){
            throw new OrganaizerException(currentUtente.getUsername(), modEvento.getUtente().getUsername());
        }
        else {
            modEvento.setTitolo(body.titolo());
            modEvento.setDescrizione(body.descrizione());
            modEvento.setDataEvento(LocalDate.parse(body.dataEvento()));
            modEvento.setLuogo(body.luogo());
        }
        return eventoRepository.save(modEvento);
    }

    public List<Evento> vediMieiEventi(Utente currentUtente){
        return eventoRepository.findByUtente(currentUtente);
    }
}
