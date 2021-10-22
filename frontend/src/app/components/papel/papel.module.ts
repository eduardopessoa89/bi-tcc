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
    


import { PapelListComponent } from './papel-list/papel-list.component';
import { PapelEditComponent } from './papel-edit/papel-edit.component';
import { PapelViewComponent } from './papel-view/papel-view.component';

const routes: Routes = [
  {
    path: '',
    component: PapelListComponent,
    
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('papel')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: PapelEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('papel')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: PapelEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('papel')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: PapelViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('papel')],
        redirectTo: '/'
      }
    }
  }
];

@NgModule({
  declarations: [
    PapelListComponent,
    PapelEditComponent,
    PapelViewComponent
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
export class PapelModule { }