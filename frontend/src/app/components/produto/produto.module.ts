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



import { ProdutoListComponent } from './produto-list/produto-list.component';
import { ProdutoEditComponent } from './produto-edit/produto-edit.component';
import { ProdutoViewComponent } from './produto-view/produto-view.component';
import { ProdutoReportComponent } from './produto-report/produto-report.component';

const routes: Routes = [
  {
    path: '',
    component: ProdutoListComponent,

    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('produto')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'create',
    component: ProdutoEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionInsert('produto')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'edit/:id',
    component: ProdutoEditComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionUpdate('produto')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'view/:id',
    component: ProdutoViewComponent,
    data: {
      permissions: {
        only: [...SecurityUtil.getPermissionRead('produto')],
        redirectTo: '/'
      }
    }
  },
  {
    path: 'reports',
    component: ProdutoReportComponent,
  }
];

@NgModule({
  declarations: [
    ProdutoListComponent,
    ProdutoEditComponent,
    ProdutoViewComponent,
    ProdutoReportComponent
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
    NgxPermissionsModule
  ],
  providers: [
  ]
})
export class ProdutoModule { }
