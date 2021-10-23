import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrudService } from './services/crud.service';
import { RouterModule } from '@angular/router';
import { DeleteConfirmationComponent } from './components/delete-confirmation/delete-confirmation.component';
import { LoadingComponent } from './components/loading/loading.component';
import { NotificationService } from './services/notification.service';
import { ModalService } from './services/modal.service';
import { NgxSmartModalModule } from 'ngx-smart-modal';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SimpleSearchComponent } from './components/simple-search/simple-search.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { ChartsModule, ThemeService } from 'ng2-charts';

@NgModule({
  declarations: [DeleteConfirmationComponent, LoadingComponent, SimpleSearchComponent, PieChartComponent, BarChartComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSmartModalModule.forRoot(),
    ChartsModule
  ],
    exports: [
        DeleteConfirmationComponent,
        LoadingComponent,
        SimpleSearchComponent,
        PieChartComponent,
        BarChartComponent 
    ],
  providers: [CrudService, NotificationService, ModalService, ThemeService]
})
export class SharedModule { }
