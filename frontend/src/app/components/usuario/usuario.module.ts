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
     


import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import { UsuarioEditComponent } from './usuario-edit/usuario-edit.component';
import { UsuarioViewComponent } from './usuario-view/usuario-view.component';

const routes: Routes = [
  {
    path: '',
    component: UsuarioListComponent,
    
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('usuario')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: UsuarioEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('usuario')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: UsuarioEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('usuario')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: UsuarioViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('usuario')],
        redirectTo: '/'
      }
    }
  }
];

@NgModule({
  declarations: [
    UsuarioListComponent,
    UsuarioEditComponent,
    UsuarioViewComponent
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
export class UsuarioModule { }