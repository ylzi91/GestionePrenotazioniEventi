package yurilenzi.GestionePrenotazioniEventi.payloads;

import yurilenzi.GestionePrenotazioniEventi.entities.Prenotazione;

public record PrenotazioneResponseDTO(Prenotazione prenotazione, Long postiRimasti) {
}
