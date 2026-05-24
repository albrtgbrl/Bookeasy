import { SalaService } from './../../../services/sale.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrenotazioneService } from '../../../services/prenotazioni.service';
import { CommonModule } from '@angular/common';
import { Sala } from '../../../models/sale.model';

@Component({
  selector: 'app-prenotazioni-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './prenotazioni-form.component.html',
  styleUrl: './prenotazioni-form.component.scss'
})
export class PrenotazioniFormComponent implements OnInit {

  idSala!: number
  prenotazioneForm: FormGroup;
  sale: Sala[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private prenotazioneService: PrenotazioneService, private salaService: SalaService) {
    this.prenotazioneForm = this.fb.group({
      idSala: [null, Validators.required],
      idUtente: [1],
      data: ['', Validators.required],
      orarioInizio: ['', Validators.required],
      orarioFine: ['', Validators.required]
    });
  }
  ngOnInit(){
    this.salaService.getAllSale().subscribe({
      next: (response => {
        this.sale = response;
      })
    })
  }

  onSubmit(): void {
    this.prenotazioneService.addPrenotazione(this.prenotazioneForm.value).subscribe({
      next: () => {
        this.successMessage = 'Prenotazione effettuata!';
        this.prenotazioneForm.reset();
      },
      error: (err) => {
        this.errorMessage = err.status === 409 ? 'Sala occupata!' : 'Errore dati.';
      }
    });
  }

}
