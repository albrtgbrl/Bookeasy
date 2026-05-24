import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sede } from '../models/sedi.model';

@Injectable({
  providedIn: 'root'
})
export class SediService {

  constructor(private http: HttpClient) { }

  getAllSedi(): Observable<Sede[]> {
    return this.http.get<Sede[]>('http://localhost:8080/sedi');
  }
}
