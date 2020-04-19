import { Component, OnInit } from '@angular/core';
import { HistoricoPrecoService } from 'src/app/shared/services/historico-preco.service';
import { HistoricoPrecoPaginatorDTO } from 'src/app/shared/model/venda.paginator.dto';
import { ActivatedRoute, Router, UrlSegment } from '@angular/router';
import { Location } from '@angular/common';
import { VendaDTO } from 'src/app/shared/model/venda.dto';
import { TranslateService } from '@ngx-translate/core';
import { MsgService } from 'src/app/shared/services/msg.service';
import { VendaService } from 'src/app/shared/services/venda.service';
import { MessageDTO } from 'src/app/shared/model/message.dto';

@Component({
  selector: 'app-historico-precos',
  templateUrl: './historico-precos.component.html',
  styleUrls: ['./historico-precos.component.css']
})
export class HistoricoPrecosComponent implements OnInit {

  paginator: HistoricoPrecoPaginatorDTO;
  venda: VendaDTO

  constructor(
    private msg: MsgService,
    private route: Router,
    private location: Location,
    private router: ActivatedRoute,
    private translate: TranslateService,
    private vendaService: VendaService,
    private historicoPrecoService: HistoricoPrecoService) { }

  ngOnInit() {

    this.router.queryParams.subscribe((params) => {
      const pageatual = params.currentPage;
      if (pageatual) {
        this.paginator = {
          currentPage: pageatual
        }
      }
    });

    this.doPaginator();
  }

  visualizarVenda(item: VendaDTO) {
    this.venda = item;
  }

  removerVenda(codigo: number) {

    const msgDTO: MessageDTO = {
      type: 'question',
      text: this.translate.get('msg.text.delete.venda')['value']
    }

    this.msg.showConfirm(msgDTO)
      .then((result) => {
        if (result.value) {
          this.vendaService.remover(codigo)
            .subscribe((response) => {
              this.msg.show(response.messages[0]);
              this.ngOnInit();
            });
        }
      });
  }

  private doPaginator() {

    this.historicoPrecoService.listar(this.paginator)
      .subscribe((result) => {
        if (result) {
          this.paginator = result;
        }
      });
    if (this.paginator && this.paginator.currentPage) {
      this.location.go(`historico-precos?currentPage=${(this.paginator.currentPage + 1)}`);
    }
  }

  isMinlength() {
    return this.paginator.currentPage <= 0;
  }

  isMaxlength() {
    return this.paginator.currentPage >= (this.quantPages() - 1);
  }

  quantPages(): number {
    let retorno = ~~(this.paginator.count / this.paginator.sizePage);
    if ((this.paginator.count % this.paginator.sizePage) !== 0) {
      retorno = retorno + 1;
    }
    return retorno;
  }

  getPages(): Array<number> {

    let i = 0;
    const quant = this.quantPages();
    const pages: Array<number> = [];
    while (quant !== i) {
      pages.push(++i);
    }

    return pages;
  }

  isPagination() {
    return this.quantPages() > 1;
  }

  isCurrentPage(page) {
    return this.paginator.currentPage === --page;
  }

  requestpage(page) {
    this.paginator.currentPage = --page;
    this.doPaginator();
  }

  previous() {
    if (this.paginator.currentPage > 0) {
      this.paginator.currentPage = this.paginator.currentPage - 1;
      this.doPaginator();
    }
  }

  next() {
    this.paginator.currentPage = this.paginator.currentPage + 1;
    this.doPaginator();
  }

}