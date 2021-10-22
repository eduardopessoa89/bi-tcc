import { Routes } from '@angular/router';
import { NgxPermissionsGuard } from 'ngx-permissions';
import { HomeComponent } from './components/home/home.component';
import { LoggedinGuard } from './shared/security/loggedin.guard';


export const AppRoutes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [LoggedinGuard],
    canActivateChild: [NgxPermissionsGuard],
    children: [
        {
    path: 'usuarios',
    loadChildren: () => import('src/app/components/usuario/usuario.module').then(m => m.UsuarioModule)
},


     {
    path: 'vendas',
    loadChildren: () => import('src/app/components/venda/venda.module').then(m => m.VendaModule)
},


   {
    path: 'itensvenda',
    loadChildren: () => import('src/app/components/itemvenda/itemvenda.module').then(m => m.ItemVendaModule)
},


  {
    path: 'produtos',
    loadChildren: () => import('src/app/components/produto/produto.module').then(m => m.ProdutoModule)
},


   {
    path: 'papeis',
    loadChildren: () => import('src/app/components/papel/papel.module').then(m => m.PapelModule)
},


  {
    path: 'papeis-permissoes',
    loadChildren: () => import('src/app/components/papel-permissao/papel-permissao.module').then(m => m.PapelPermissaoModule)
}, {
    path: 'permissoes',
    loadChildren: () => import('src/app/components/permissao/permissao.module').then(m => m.PermissaoModule)
},


   
    ]
  },
  { path: 'login', loadChildren: () => import('src/app/components/login/login.module').then(m => m.LoginModule) },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];