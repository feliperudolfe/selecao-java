import { ProdutoDTO } from './produto.dto';
import { DistribuidoraDTO } from './distribuidora.dto';

export interface VendaDTO {
  codigo?: number;
  dataColeta: any;
  valorVenda: number;
  valorCompra: number;
  produto?: ProdutoDTO;
  distribuidora?: DistribuidoraDTO;
}