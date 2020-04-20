import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HistoricoPrecoPaginatorDTO } from '../model/venda.paginator.dto';
import { ResponseDTO } from '../model/response.dto';

const PATH = {
  historicoPrecos: '/historico-precos'
};

@Injectable()
export class HistoricoPrecoService {

  constructor(private http: HttpClient) {}

  listar(paginator: HistoricoPrecoPaginatorDTO): Observable<HistoricoPrecoPaginatorDTO> {

    let httpParams = new HttpParams().set('sortingColumn', 'name');
    for (const i in paginator) {
      if (i !== 'list' && paginator[i] != null) {
        httpParams = httpParams.set(i, paginator[i]);
      }
    }

    return this.http.get<HistoricoPrecoPaginatorDTO>(`${environment.urlbase}${PATH.historicoPrecos}`, { params: httpParams });
  }

  mediaPorMunicio(idMunicipio: number): Observable<ResponseDTO> {
    const httpParams = new HttpParams().set('municipio', idMunicipio + '');
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.historicoPrecos}/media`, { params: httpParams });
  }

  mediaPorBandeira(idBandeira: number): Observable<ResponseDTO> {
    const httpParams = new HttpParams().set('bandeira', idBandeira + '');
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.historicoPrecos}/media`, { params: httpParams });
  }

}