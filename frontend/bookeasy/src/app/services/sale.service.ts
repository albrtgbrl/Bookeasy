import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sala, SalaDTO } from '../models/sale.model';

@Injectable({
  providedIn: 'root'
})
export class SalaService {
  private apiUrl = 'http://localhost:8080/sale';

  constructor(private http: HttpClient) {}

  getAllSale(): Observable<Sala[]> {
    return this.http.get<Sala[]>(this.apiUrl);
  }

  createSala(salaDTO: SalaDTO): Observable<Sala> {
    return this.http.post<Sala>(`${this.apiUrl}/crea`, salaDTO);
  }

  getPrenotazioniBySala(idSala: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${idSala}/prenotazioni`);
  }

  verificaDisponibilita(idSala: number, data: string, oraInizio: string, oraFine: string): Observable<boolean> {
    const params = { data, oraInizio, oraFine };
    return this.http.get<boolean>(`${this.apiUrl}/${idSala}/disponibilita`, { params });
  }
}
