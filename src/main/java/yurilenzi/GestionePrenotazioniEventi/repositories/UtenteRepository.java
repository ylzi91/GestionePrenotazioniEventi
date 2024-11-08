package yurilenzi.GestionePrenotazioniEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente, String> {

}
