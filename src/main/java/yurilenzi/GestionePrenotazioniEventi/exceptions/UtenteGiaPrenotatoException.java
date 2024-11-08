package yurilenzi.GestionePrenotazioniEventi.exceptions;

public class UtenteGiaPrenotatoException extends RuntimeException {
    public UtenteGiaPrenotatoException(String usernmae) {
        super(
               usernmae + " già prenotato"
        );
    }
}
