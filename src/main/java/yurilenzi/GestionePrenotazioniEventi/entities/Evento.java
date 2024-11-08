package yurilenzi.GestionePrenotazioniEventi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    private Long id;
    private String titolo, descrizione, luogo;
    private int maxPartecipanti;
    private LocalDate dataEvento;
    @ManyToOne
    private Utente utente;

    public Evento(String titolo, String descrizione, String luogo, int maxPartecipanti, LocalDate dataEvento, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.maxPartecipanti = maxPartecipanti;
        this.dataEvento = dataEvento;
        this.utente = utente;
    }
}
