import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PapelPermissaoComponent } from './papel-permissao.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { NgSelectModule } from '@ng-select/ng-select';

const routes: Routes = [
  {
    path: '',
    component: PapelPermissaoComponent
  },
];

@NgModule({
  declarations: [PapelPermissaoComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    SharedModule,
    ReactiveFormsModule,
    NgSelectModule,
  ]
})
export class PapelPermissaoModule { }
