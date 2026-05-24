export interface PrenotazioneRequest {
  idSala: number;
  idUtente: number;
  data: string;
  orarioInizio: string;
  orarioFine: string;
}

export interface Prenotazione {
  idPrenotazione: number;
  data: string;
  oraInizio: string;
  oraFine: string;
  sala: any;
  utente: any;
}
