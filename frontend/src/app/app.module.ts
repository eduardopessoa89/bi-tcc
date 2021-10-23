import { BrowserModule } from '@angular/platform-browser';
import { Injector, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './components/home/home.module';
import { RouterModule } from '@angular/router';
import { AppRoutes } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { setAppInjector } from './shared/helpers/app.injector';
import { NgxPermissionsModule } from 'ngx-permissions';
import { CubejsClientModule } from '@cubejs-client/ngx';

const cubejsOptions = {
  token: "",
  options: { apiUrl: "http://localhost:4000/cubejs-api/v1" }
};

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    HomeModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgxPermissionsModule.forRoot(),
    RouterModule.forRoot(AppRoutes),
    CubejsClientModule.forRoot(cubejsOptions),
    ToastrModule.forRoot({
      progressBar: true,
      closeButton: true,
      maxOpened: 1,
      autoDismiss: true,
      preventDuplicates: true
    })
  ],
  providers: [],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(injector: Injector) {
    setAppInjector(injector);
  }
}
