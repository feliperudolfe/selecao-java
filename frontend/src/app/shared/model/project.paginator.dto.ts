import { ProjectDTO } from './project.dto';

export interface ProjectPaginatorDTO {
  currentPage: number;
  sizePage: number;
  count: number;
  sortingColumn: string;
  ordination: string;
  list: Array<ProjectDTO>;
}