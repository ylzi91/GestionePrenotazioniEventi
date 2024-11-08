package yurilenzi.GestionePrenotazioniEventi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String username) {
        super(
                username + " non trovato"
        );
    }
    public NotFoundException(Long id) {
        super(
                id + " non trovato"
        );
    }
}
