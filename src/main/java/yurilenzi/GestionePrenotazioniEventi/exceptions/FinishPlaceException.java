package yurilenzi.GestionePrenotazioniEventi.exceptions;

public class FinishPlaceException extends RuntimeException {
    public FinishPlaceException() {
        super("Non ci sono più posti disponibili");
    }
}
