import { BandeiraDTO } from './bandeira.dto';

export interface ProdutoDTO {
  codigo?: number;
  nome?: string;
  unidadeMedida?: string;
  bandeira?: BandeiraDTO;
}