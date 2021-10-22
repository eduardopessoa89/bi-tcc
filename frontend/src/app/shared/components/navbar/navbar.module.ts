import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar.component';
import { RouterModule } from '@angular/router';
import { NgxPermissionsModule } from 'ngx-permissions';



@NgModule({
  declarations: [NavbarComponent],
  imports: [
    CommonModule,
    NgxPermissionsModule,
    RouterModule
  ],
  exports: [NavbarComponent]
})
export class NavbarModule { }
