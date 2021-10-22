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
   


import { PermissaoListComponent } from './permissao-list/permissao-list.component';
import { PermissaoEditComponent } from './permissao-edit/permissao-edit.component';
import { PermissaoViewComponent } from './permissao-view/permissao-view.component';

const routes: Routes = [
  {
    path: '',
    component: PermissaoListComponent,
    
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('permissao')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: PermissaoEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('permissao')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: PermissaoEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('permissao')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: PermissaoViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('permissao')],
        redirectTo: '/'
      }
    }
  }
];

@NgModule({
  declarations: [
    PermissaoListComponent,
    PermissaoEditComponent,
    PermissaoViewComponent
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
export class PermissaoModule { }