package yurilenzi.GestionePrenotazioniEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {

}
