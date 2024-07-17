import { Component, OnInit, OnDestroy, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe, } from '@angular/common';
import { PaginatorModule } from 'primeng/paginator';
import { ErrorMessagePipe, TypeValidator } from '@my/core';
import { IdiomasViewModelService } from './servicios.service';

@Component({
    selector: 'app-idiomas-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [RouterLink, PaginatorModule]
})
export class IdiomasListComponent implements OnChanges, OnDestroy {
  @Input() page = 0

  constructor(protected vm: IdiomasViewModelService) { }
  public get VM(): IdiomasViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.list()
  }
  ngOnDestroy(): void { this.vm.clear(); }
}
@Component({
    selector: 'app-idiomas-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class IdiomasAddComponent implements OnInit {
  constructor(protected vm: IdiomasViewModelService) { }
  public get VM(): IdiomasViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
    selector: 'app-idiomas-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class IdiomasEditComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: IdiomasViewModelService, protected router: Router) { }
  public get VM(): IdiomasViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}
@Component({
    selector: 'app-idiomas-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [DatePipe]
})
export class IdiomasViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: IdiomasViewModelService, protected router: Router) { }
  public get VM(): IdiomasViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const IDIOMAS_COMPONENTES = [
  IdiomasListComponent, IdiomasAddComponent, IdiomasEditComponent, IdiomasViewComponent,
];
