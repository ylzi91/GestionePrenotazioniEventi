package yurilenzi.GestionePrenotazioniEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yurilenzi.GestionePrenotazioniEventi.entities.Evento;
import yurilenzi.GestionePrenotazioniEventi.entities.Prenotazione;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository <Prenotazione, Long> {

    @Query("select count(p) from Prenotazione p where p.evento = :evento")
    Long contaPrenotazioniPerEvento(Evento evento);

    @Query("select p from Prenotazione p where p.evento = :evento and p.utente = :utente")
    Optional<Prenotazione> vediSeUtenteGiaPrenotato(Evento evento, Utente utente);

    List<Prenotazione> findByUtente(Utente utente);
}
