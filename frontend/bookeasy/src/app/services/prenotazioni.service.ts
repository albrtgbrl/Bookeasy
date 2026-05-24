import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prenotazione, PrenotazioneRequest } from '../models/prenotazioni.model';

@Injectable({
  providedIn: 'root'
})
export class PrenotazioneService {
  private apiUrl = 'http://localhost:8080/prenotazioni';

  constructor(private http: HttpClient) {}

  addPrenotazione(request: PrenotazioneRequest): Observable<Prenotazione> {
    const params = {
      idSala: request.idSala.toString(),
      idUtente: request.idUtente.toString(),
      data: request.data,
      orarioInizio: request.orarioInizio,
      orarioFine: request.orarioFine
    };
    return this.http.post<Prenotazione>(`${this.apiUrl}/nuova-prenotazione`, null, { params });
  }

  getPrenotazioniBySala(idSala: number): Observable<Prenotazione[]> {
    return this.http.get<Prenotazione[]>(`${this.apiUrl}/sala/${idSala}`);
  }

  deletePrenotazione(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
