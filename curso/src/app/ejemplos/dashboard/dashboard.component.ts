import { Component } from '@angular/core';
import { AjaxWaitComponent, HomeComponent } from 'src/app/main';
import { DemosComponent } from '../demos/demos.component';
import GraficoSvgComponent from 'src/lib/my-core/components/grafico-svg/grafico-svg.component';
import { NotificationComponent } from "../../main/notification/notification.component";
import { CommonModule } from '@angular/common';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
import { FormularioComponent } from '../formulario/formulario.component';
import { ContactosComponent } from 'src/app/contactos';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NotificationComponent, CommonModule, AjaxWaitComponent, CalculadoraComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  menu = [
    { texto: 'contactos', icono: 'fa-solid fa-address-book', componente: ContactosComponent},
    { texto: 'inicio', icono: 'fa-solid fa-house', componente: HomeComponent },
    { texto: 'demos', icono: 'fa-solid fa-person-chalkboard', componente: DemosComponent},
    { texto: 'calculadora', icono: 'fa-solid fa-calculator', componente: CalculadoraComponent },
    { texto: 'gráfico', icono: 'fa-solid fa-image', componente: GraficoSvgComponent },
    { texto: 'formulario', icono: 'fa-solid fa-chalkboard-user', componente: FormularioComponent},
  ]
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  actual: any = this.menu[0].componente

  seleccionar(indice: number) {
    this.actual = this.menu[indice].componente
  }
}
