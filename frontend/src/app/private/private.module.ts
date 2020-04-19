import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { PrivateTemplateComponent } from './private-template/private-template.component';
import { TranslateModule } from '@ngx-translate/core';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { ServicesModule } from '../shared/services/services.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { GroupByPipe } from '../shared/pipe/group-by.pipe';

import { FormsModule } from '@angular/forms';
import { HistoricoPrecosComponent } from './historico-precos/historico-precos.component';

@NgModule({
  declarations: [
    PrivateTemplateComponent,
    GroupByPipe,
    HistoricoPrecosComponent
  ],
  imports: [
    CommonModule,
    ServicesModule,
    FormsModule,
    ReactiveFormsModule,
    PrivateRoutingModule,
    TranslateModule,
    SweetAlert2Module,
    ChartsModule
  ],
})
export class PrivateModule { }
