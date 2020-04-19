import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MsgService } from 'src/app/shared/services/msg.service';
import { VendaDTO } from 'src/app/shared/model/venda.dto';
import { VendaService } from 'src/app/shared/services/venda.service';
import { MessageDTO } from 'src/app/shared/model/message.dto';
import { OptionDTO } from 'src/app/shared/model/options.dto';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { DistribuidoraService } from 'src/app/shared/services/distribuidora.service';

@Component({
  selector: 'app-editar-venda',
  templateUrl: './editar-venda.component.html',
  styleUrls: ['./editar-venda.component.css']
})
export class EditarVendaComponent implements OnInit {

  venda: VendaDTO;
  produtos: Array<OptionDTO>;
  distribuidoras: Array<OptionDTO>;

  form: FormGroup = new FormGroup({
    dataColeta: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]),
    valorVenda: new FormControl(null, [Validators.required]),
    valorCompra: new FormControl(null, []),
    produto: new FormControl(null, [Validators.required]),
    distribuidora: new FormControl(null, [Validators.required]),
  });

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private translate: TranslateService,
    private msg: MsgService,
    private produtoService: ProdutoService,
    private distribuidoraService: DistribuidoraService,
    private vendaService: VendaService) { }

  ngOnInit() {

    this.produtoService.listarOptions()
      .subscribe((result) => {
        console.log("result: ", result);
        this.produtos = result;
      });

    this.distribuidoraService.listarOptions()
      .subscribe((result) => {
        console.log("result: ", result);
        this.distribuidoras = result;
      });

    this.activatedRoute.params.forEach(params => {
      const idVenda = params['idVenda'];
      if (idVenda) {

        this.vendaService.get(idVenda)
          .subscribe((result) => {
            if (result) {

              console.log("result: ", result);

              this.venda = result.data;
              this.form.patchValue({
                dataColeta: this.venda.dataColeta,
                valorVenda: this.venda.valorVenda,
                valorCompra: this.venda.valorCompra,
                produto: this.venda.produto.codigo,
                distribuidora: this.venda.distribuidora.codigo,
              });
            }
          });

      }
    });
  }

  register() {

    const msgDTO: MessageDTO = {
      type: 'question',
      text: this.translate.get('msg.text.atualizar.venda')['value']
    }

    this.msg.showConfirm(msgDTO)
      .then((result) => {

        const venda: VendaDTO = {
          codigo: (this.venda) ? this.venda.codigo : null,
          dataColeta: this.parse(this.form.controls.dataColeta.value),
          valorVenda: this.form.controls.valorVenda.value,
          valorCompra: this.form.controls.valorCompra.value,
          produto: {
            codigo: this.form.controls.produto.value
          },
          distribuidora: {
            codigo: this.form.controls.distribuidora.value
          }
        };

        if (result.value) {
          this.vendaService.atualizar(venda)
            .subscribe((response) => {
              this.router.navigate(['private/historico-precos']);
              this.msg.show(response.messages[0]);
            });
        }
      });
  }

  parse(value: any): Date | null {
    if ((typeof value === 'string') && (value.includes('/'))) {
      const str = value.split('/');

      const year = Number(str[2]);
      const month = Number(str[1]) - 1;
      const date = Number(str[0]);

      return new Date(year, month, date);
    } else if((typeof value === 'string') && value === '') {
      return new Date();
    }
    const timestamp = typeof value === 'number' ? value : Date.parse(value);
    return isNaN(timestamp) ? null : new Date(timestamp);
  }

}
