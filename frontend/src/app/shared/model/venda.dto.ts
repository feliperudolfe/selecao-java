import { ProdutoDTO } from './produto.dto';
import { DistribuidoraDTO } from './distribuidora.dto';

export interface VendaDTO {
  codigo?: number;
  dataColeta: string;
  valorVenda: number;
  valorCompra: number;
  produto?: ProdutoDTO;
  distribuidora?: DistribuidoraDTO;
}