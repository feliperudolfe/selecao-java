import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';
import { ExecutionPaginatorDTO } from '../model/execution.paginator.dto';

const PATH = {
  executions: '/executions',
}

@Injectable()
export class ExecutionService {

  constructor(private http: HttpClient) { }

  listarPorProjeto(token: string, paginator: ExecutionPaginatorDTO): Observable<ExecutionPaginatorDTO> {

    let httpParams = new HttpParams().set('sortingColumn', 'increment');
    for (const i in paginator) {
      if (i !== 'list' && paginator[i] != null) {
        httpParams = httpParams.set(i, paginator[i]);
      }
    }

    const httpOptions = {
      params: httpParams,
      headers: new HttpHeaders({
        'Project-Token': token,
      })
    }

    return this.http.get<ExecutionPaginatorDTO>(`${environment.urlbase}${PATH.executions}`, httpOptions);
  }

  obterPorProjetoEIncrement(token: string, increment: number): Observable<ResponseDTO> {

    // const httpParams = new HttpParams().set('increment', increment.toString());

    const httpOptions = {
      // params: httpParams,
      headers: new HttpHeaders({
        'Project-Token': token,
      })
    }

    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.executions}/${increment}`, httpOptions);
  }

}