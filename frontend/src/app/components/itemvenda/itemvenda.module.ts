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
  


import { ItemVendaListComponent } from './itemvenda-list/itemvenda-list.component';
import { ItemVendaEditComponent } from './itemvenda-edit/itemvenda-edit.component';
import { ItemVendaViewComponent } from './itemvenda-view/itemvenda-view.component';

const routes: Routes = [
  {
    path: '',
    component: ItemVendaListComponent,
    
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('itemvenda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: ItemVendaEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('itemvenda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: ItemVendaEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('itemvenda')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: ItemVendaViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('itemvenda')],
        redirectTo: '/'
      }
    }
  }
];

@NgModule({
  declarations: [
    ItemVendaListComponent,
    ItemVendaEditComponent,
    ItemVendaViewComponent
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
export class ItemVendaModule { }