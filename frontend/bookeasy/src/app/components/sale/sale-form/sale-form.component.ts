import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SalaService } from '../../../services/sale.service';
import { CommonModule } from '@angular/common';
import { Sede } from '../../../models/sedi.model';
import { SediService } from '../../../services/sedi.service';

@Component({
  selector: 'app-sale-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './sale-form.component.html',
  styleUrl: './sale-form.component.scss'
})
export class SaleFormComponent implements OnInit{

  salaForm: FormGroup;
  sedi: Sede[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private salaService: SalaService, private sediService: SediService) {
    this.salaForm = this.fb.group({
      nome: ['', Validators.required],
      capienza: [null, [Validators.required, Validators.min(1)]],
      idSede: [null, Validators.required]
    });
  }

  ngOnInit(): void {
      this.sediService.getAllSedi().subscribe({
        next: (res) => this.sedi = res,
        error: (err) => console.error('Errore nel caricamento delle sedi:', err)
      });
    }

  onSubmit(): void {
    this.salaService.createSala(this.salaForm.value).subscribe({
      next: () => {
        this.successMessage = 'Sala creata con successo';
        this.salaForm.reset();
      },
      error: () => this.errorMessage = 'Errore nel salvataggio.'
    });
  }

}
