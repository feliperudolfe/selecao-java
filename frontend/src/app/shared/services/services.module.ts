import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';
import { MsgService } from './msg.service';
import { HistoricoPrecoService } from './historico-preco.service';
import { VendaService } from './venda.service';
import { ProdutoService } from './produto.service';
import { DistribuidoraService } from './distribuidora.service';
import { ImportacaoArquivoService } from './importacao-arquivo.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    MsgService,
    UserService,
    HistoricoPrecoService,
    VendaService,
    ProdutoService,
    DistribuidoraService,
    ImportacaoArquivoService
  ]
})
export class ServicesModule { }