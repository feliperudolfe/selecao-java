import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HistoricoPrecoService } from 'src/app/shared/services/historico-preco.service';
import { HistoricoPrecoPaginatorDTO } from 'src/app/shared/model/venda.paginator.dto';
import { ActivatedRoute, Router, UrlSegment } from '@angular/router';
import { Location } from '@angular/common';
import { VendaDTO } from 'src/app/shared/model/venda.dto';
import { TranslateService } from '@ngx-translate/core';
import { MsgService } from 'src/app/shared/services/msg.service';
import { VendaService } from 'src/app/shared/services/venda.service';
import { MessageDTO } from 'src/app/shared/model/message.dto';
import { ImportacaoArquivoService } from 'src/app/shared/services/importacao-arquivo.service';
import * as $ from 'jquery';
import { OptionDTO } from 'src/app/shared/model/options.dto';

@Component({
  selector: 'app-historico-precos',
  templateUrl: './historico-precos.component.html',
  styleUrls: ['./historico-precos.component.css']
})
export class HistoricoPrecosComponent implements OnInit {

  paginator: HistoricoPrecoPaginatorDTO;
  venda: VendaDTO;
  fileData: File = null;
  previewUrl: any = null;
  fileUploadProgress: string = null;
  uploadedFilePath: string = null;

  quantPaginas: Array<OptionDTO>;

  isDataColeta = false;
  isDistribuidora = false;
  isRegiao = false;

  constructor(
    private msg: MsgService,
    private location: Location,
    private router: ActivatedRoute,
    private translate: TranslateService,
    private vendaService: VendaService,
    private importacaoArquivoService: ImportacaoArquivoService,
    private historicoPrecoService: HistoricoPrecoService) { }

  ngOnInit() {

    if (this.paginator === undefined) {
      this.paginator = {
        currentPage: 0
      };
    }

    this.quantPaginas = [{codigo: 10, valor: '10'}, {codigo: 20, valor: '20'}, {codigo: 50, valor: '50'}, {codigo: 100, valor: '100'}];
    this.paginator.sizePage = this.quantPaginas[0].codigo;

    this.router.queryParams.subscribe((params) => {
      const pageatual = params.currentPage;
      if (pageatual) {
        this.paginator.currentPage = pageatual;
      }
    });

    this.router.params.subscribe((params) => {
      const dataColeta = params.dataColeta;
      if (dataColeta) {
        this.isDataColeta = true
        this.paginator.dataColeta = dataColeta;
      }

      const idDistribuidora = params.idDistribuidora;
      if (idDistribuidora) {
        this.isDistribuidora = true
        this.paginator.distribuidora = idDistribuidora;
      }

      const regiao = params.regiao;
      if (regiao) {
        this.isRegiao = true
        this.paginator.regiao = regiao;
      }
    });

    if (this.paginator === undefined) {
      this.paginator = {
        currentPage: 0
      };
    }

    this.doPaginator();
  }

  onChangeQuantPage() {
    this.doPaginator();
  }

  fileProgress(fileInput: any) {
    this.fileData = <File>fileInput.target.files[0];
    this.preview();
  }

  preview() {

    const mimeType = this.fileData.type;
    if (mimeType.match(/vnd.ms-excel\/*/) == null) {
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    reader.onload = (_event) => {
      this.previewUrl = reader.result;
    };
  }

  upload() {
    this.importacaoArquivoService.importarBase64CSV(this.previewUrl)
      .subscribe((response) => {
        this.msg.show(response.messages[0]).then(() => {
          $('#importar-csv-btn').click();
          this.previewUrl = null;
          this.ngOnInit();
        });
      },
      (response) => {
        this.msg.show(response.error.messages[0]);
      });
  }

  visualizarVenda(item: VendaDTO) {
    this.venda = item;
  }

  limparVenda() {
    this.venda = undefined;
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