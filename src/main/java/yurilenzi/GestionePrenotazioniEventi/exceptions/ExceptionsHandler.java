package yurilenzi.GestionePrenotazioniEventi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yurilenzi.GestionePrenotazioniEventi.payloads.ErrorResponseDTO;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleUnauthorizedRequest(UnauthorizedException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadRequest(AuthorizationDeniedException  ex){
        return new ErrorResponseDTO("Non hai i privilegi per questa operazione");
    }

    @ExceptionHandler(OrganaizerException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadRequest(OrganaizerException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(SameUtenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(SameUtenteException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(BadRequestException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(FinishPlaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(FinishPlaceException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(UtenteGiaPrenotatoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(UtenteGiaPrenotatoException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleBadRequest(NotFoundException  ex){
        return new ErrorResponseDTO(ex.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(DateTimeParseException  ex){
        return new ErrorResponseDTO("Il formato della data e sbagliato yyyy-mm-dd");
    }


}
