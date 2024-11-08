package yurilenzi.GestionePrenotazioniEventi.exceptions;

public class SameUtenteException extends RuntimeException {
    public SameUtenteException(String username) {
        super(
                username + " già in uso"
        );
    }
}
