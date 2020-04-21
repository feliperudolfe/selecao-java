import { MunicipioDTO } from './municipio.dto';

export interface DistribuidoraDTO {
  codigo?: number;
  cnpj?: string;
  nome?: string;
  municipio?: MunicipioDTO;
}