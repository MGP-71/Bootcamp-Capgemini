import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';
import { NotificationComponent, NotificationModalComponent } from './main';
import { CalculadoraComponent, DemosComponent } from './ejemplos';
import { NotificationService, NotificationType } from './common-services';
import { HomeComponent } from "./main/home/home.component";
import { DashboardComponent } from "./ejemplos/dashboard/dashboard.component";
import { FormularioComponent } from "./ejemplos/formulario/formulario.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NotificationComponent, NotificationModalComponent, DemosComponent, HomeComponent, DashboardComponent, CalculadoraComponent, FormularioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  // constructor(log: LoggerService) {
  //   log.error('Es un error')
  //   log.warn('Es un warn')
  //   log.info('Es un info')
  //   log.log('Es un log')
  // }
//  constructor(private notify: NotificationService) { }

//  // eslint-disable-next-line @angular-eslint/use-lifecycle-interface
//  ngOnInit(): void {
//   this.notify.add('Aplicación arrancada', NotificationType.info)
//  }
}
