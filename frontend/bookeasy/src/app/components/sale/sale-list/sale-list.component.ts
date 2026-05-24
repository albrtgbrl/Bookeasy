import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalaService } from '../../../services/sale.service';
import { Sala } from '../../../models/sale.model';

@Component({
  selector: 'app-sala-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sale-list.component.html',
  styleUrls: ['./sale-list.component.scss']
})
export class SaleListComponent implements OnInit {
  sale: Sala[] = [];

  constructor(private salaService: SalaService) {}

  ngOnInit(): void {
    this.salaService.getAllSale().subscribe({
      next: (data) => this.sale = data,
      error: (err) => console.error('Errore nel recupero delle sale', err)
    });
  }
}
