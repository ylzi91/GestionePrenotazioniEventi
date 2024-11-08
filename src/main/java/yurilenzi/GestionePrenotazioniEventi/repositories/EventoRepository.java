package yurilenzi.GestionePrenotazioniEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurilenzi.GestionePrenotazioniEventi.entities.Evento;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Long> {

    List<Evento> findByUtente(Utente utente);
}
