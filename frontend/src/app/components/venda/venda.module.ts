import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgxSmartModalModule } from 'ngx-smart-modal';
import { PaginationModule } from 'src/app/shared/components/pagination/pagination.module';
import { SecurityUtil } from 'src/app/shared/utils/security.util';
import { NgxPermissionsModule } from 'ngx-permissions';
   


import { VendaListComponent } from './venda-list/venda-list.component';
import { VendaEditComponent } from './venda-edit/venda-edit.component';
import { VendaViewComponent } from './venda-view/venda-view.component';

const routes: Routes = [
  {
    path: '',
    component: VendaListComponent,
    
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('venda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: VendaEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('venda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: VendaEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('venda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: VendaViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('venda')],
        redirectTo: '/'
      }
    }
  }
];

@NgModule({
  declarations: [
    VendaListComponent,
    VendaEditComponent,
    VendaViewComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    ReactiveFormsModule,
    NgSelectModule,
    NgxSmartModalModule.forChild(),
    PaginationModule,
    NgxPermissionsModule,
       
  ],
  providers: [
  ]
})
export class VendaModule { }