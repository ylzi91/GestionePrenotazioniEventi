package yurilenzi.GestionePrenotazioniEventi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yurilenzi.GestionePrenotazioniEventi.payloads.ErrorResponseDTO;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleUnauthorizedRequest(UnauthorizedException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

}
