import { Component } from '@angular/core';
import { HomeComponent } from 'src/app/main';
import { DemosComponent } from '../demos/demos.component';
import GraficoSvgComponent from 'src/lib/my-core/components/grafico-svg/grafico-svg.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  menu = [
    { texto: 'inicio', icon: '', componente: HomeComponent },
    { texto: 'demos', icon: '', componente: DemosComponent },
    { texto: 'gráfico', icon: '', componente: GraficoSvgComponent }
  ]
  actual: any = this.menu[0].componente
  seleccionar(indice: number) {
    this.actual = this.menu[indice].componente
  }
}
