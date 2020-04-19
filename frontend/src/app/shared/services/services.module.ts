import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';
import { MsgService } from './msg.service';
import { HistoricoPrecoService } from './historico-preco.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    MsgService,
    UserService,
    HistoricoPrecoService
  ]
})
export class ServicesModule { }