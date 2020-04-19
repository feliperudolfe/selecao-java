import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';
import { VendaDTO } from '../model/venda.dto';

const PATH = {
  venda: '/vendas'
};

@Injectable()
export class VendaService {

  constructor(private http: HttpClient) {}

  remover(codigo: number): Observable<ResponseDTO> {
    return this.http.delete<ResponseDTO>(`${environment.urlbase}${PATH.venda}/${codigo}`);
  }

  get(codigo: number): Observable<ResponseDTO> {
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.venda}/${codigo}`);
  }

  atualizar(venda: VendaDTO): Observable<ResponseDTO> {
    return this.http.put<ResponseDTO>(`${environment.urlbase}${PATH.venda}/${venda.codigo}`, venda);
  }

}