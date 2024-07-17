import { NgModule } from '@angular/core';
import { IDIOMAS_COMPONENTES, IdiomasAddComponent, IdiomasEditComponent, IdiomasListComponent, IdiomasViewComponent } from './componente.component';
import { RouterModule, Routes } from '@angular/router';
import { InRoleCanActivate } from '../security';

export const routes: Routes = [
  { path: '', component: IdiomasListComponent },
  { path: 'add', component: IdiomasAddComponent/*, canActivate: [ InRoleCanActivate('Empleados')]*/ },
  { path: ':id/edit', component: IdiomasEditComponent/*, canActivate: [ InRoleCanActivate('Empleados')]*/ },
  { path: ':id', component: IdiomasViewComponent/*, canActivate: [ InRoleCanActivate('Empleados')]*/ },
  { path: ':id/:kk', component: IdiomasViewComponent/*, canActivate: [ InRoleCanActivate('Empleados')]*/ },
]
@NgModule({
  declarations: [],
  imports: [ IDIOMAS_COMPONENTES, RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class IdiomasModule { }
