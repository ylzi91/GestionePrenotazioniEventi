package yurilenzi.GestionePrenotazioniEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yurilenzi.GestionePrenotazioniEventi.entities.Utente;
import yurilenzi.GestionePrenotazioniEventi.exceptions.UnauthorizedException;
import yurilenzi.GestionePrenotazioniEventi.payloads.LoginDTO;
import yurilenzi.GestionePrenotazioniEventi.payloads.LoginResponseDTO;
import yurilenzi.GestionePrenotazioniEventi.tools.JWT;

@Service
public class AuthService {
    @Autowired
    private JWT jwt;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialAndResponseToken(LoginDTO body){
        Utente found = utenteService.findByUsername(body.username());
        if(bcrypt.matches(body.password(), found.getPassword())){
            String accesToken = jwt.createToken(found);
            return accesToken;
        }
        else throw new UnauthorizedException("Credenziali errate");

    }
}
