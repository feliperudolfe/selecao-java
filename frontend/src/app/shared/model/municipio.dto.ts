import { EstadoDTO } from './estado.dto';

export interface MunicipioDTO {
  codigo?: number;
  nome: number;
  uf: EstadoDTO;
}