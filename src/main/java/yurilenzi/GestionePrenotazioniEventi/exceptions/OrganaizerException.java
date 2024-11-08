package yurilenzi.GestionePrenotazioniEventi.exceptions;

public class OrganaizerException extends RuntimeException {
    public OrganaizerException(String currentUsername, String username) {
        super(
               currentUsername + " non può modificare un evento di " + username
        );
    }
}
