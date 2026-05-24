import { Component, OnInit } from '@angular/core';
import { Prenotazione } from '../../../models/prenotazioni.model';
import { PrenotazioneService } from '../../../services/prenotazioni.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Sala } from '../../../models/sale.model';
import { SalaService } from '../../../services/sale.service';

@Component({
  selector: 'app-prenotazioni-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './prenotazioni-list.component.html',
  styleUrl: './prenotazioni-list.component.scss'
})
export class PrenotazioniListComponent implements OnInit {

  idSalaRicerca!: number;
  sale: Sala[] = [];
  prenotazioni: Prenotazione[] = [];
  message = '';

  constructor(private prenotazioneService: PrenotazioneService, private salaService: SalaService) {}
  ngOnInit(){
    this.salaService.getAllSale().subscribe({
      next: (response => {
        this.sale = response;
      })
    })
  }

  cercaPrenotazioni(): void {
    if (this.idSalaRicerca) {
      this.prenotazioneService.getPrenotazioniBySala(this.idSalaRicerca).subscribe({
        next: (data) => {
          this.prenotazioni = data;
          this.message = data.length === 0 ? 'Nessuna prenotazione trovata per questa sala.' : '';
        },
        error: () => this.message = 'Errore nel recupero delle prenotazioni.'
      });
    }
  }

  cancellaPrenotazione(id: number): void {
    if (confirm('Vuoi davvero cancellare questa prenotazione?')) {
      this.prenotazioneService.deletePrenotazione(id).subscribe({
        next: () => {
          this.prenotazioni = this.prenotazioni.filter(p => p.idPrenotazione !== id);
        },
        error: () => alert('Impossibile cancellare la prenotazione.')
      });
    }
  }

}
