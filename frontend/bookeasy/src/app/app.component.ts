import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SaleFormComponent } from './components/sale/sale-form/sale-form.component';
import { PrenotazioniFormComponent } from './components/prenotazioni/prenotazioni-form/prenotazioni-form.component';
import { PrenotazioniListComponent } from './components/prenotazioni/prenotazioni-list/prenotazioni-list.component';
import { SaleListComponent } from './components/sale/sale-list/sale-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    SaleFormComponent,
    PrenotazioniFormComponent,
    PrenotazioniListComponent,
    SaleListComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  activeTab = 'sale-list';

  setTab(tabName: string): void {
    this.activeTab = tabName;
  }
}
