import { VendaDTO } from './venda.dto';

export interface HistoricoPrecoPaginatorDTO {
  currentPage: number;
  sizePage: number;
  count: number;
  list: Array<VendaDTO>;
}