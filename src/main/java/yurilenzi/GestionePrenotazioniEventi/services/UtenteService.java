package yurilenzi.GestionePrenotazioniEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.NotFoundException;
import yurilenzi.GestionePrenotazioniEventi.exceptions.SameUtenteException;
import yurilenzi.GestionePrenotazioniEventi.payloads.NewUtenteDTO;
import yurilenzi.GestionePrenotazioniEventi.repositories.UtenteRepository;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    PasswordEncoder bcrypt;

    public Utente findByUsername(String username){
        Utente found = null;
        found = utenteRepository.findById(username).orElseThrow(()-> new NotFoundException(username));
        return found;
    }

    public Utente saveUtente(NewUtenteDTO body){
        if(utenteRepository.findById(body.username()).isPresent()){
            throw new SameUtenteException(body.username());
        }
        return utenteRepository.save(new Utente(body.username(), body.nome(), body.cognome(), bcrypt.encode(body.password())));

    }

    public List<Utente> vediTutti(){
        return utenteRepository.findAll();
    }


}
