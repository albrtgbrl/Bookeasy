import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrenotazioneService } from '../../../services/prenotazioni.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-prenotazioni-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './prenotazioni-form.component.html',
  styleUrl: './prenotazioni-form.component.scss'
})
export class PrenotazioniFormComponent {

  prenotazioneForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private prenotazioneService: PrenotazioneService) {
    this.prenotazioneForm = this.fb.group({
      idSala: [null, Validators.required],
      idUtente: [null, Validators.required],
      data: ['', Validators.required],
      orarioInizio: ['', Validators.required],
      orarioFine: ['', Validators.required]
    });
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
