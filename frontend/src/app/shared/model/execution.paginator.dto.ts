import { ExecutionDTO } from './execution.dto';

export interface ExecutionPaginatorDTO {
  currentPage: number;
  sizePage: number;
  count: number;
  sortingColumn: string;
  ordination: string;
  list: Array<ExecutionDTO>;
}