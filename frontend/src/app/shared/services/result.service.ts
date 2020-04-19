import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';

const PATH = {
  results: '/results',
  listByProject: '/results/project',
  listByProjectAndIncrement: '/results/increment'
}

@Injectable()
export class ResultService {

  constructor(private http: HttpClient) { }

  listarPorProjeto(token: string): Observable<ResponseDTO> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Project-Token': token,
      })
    }

    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.listByProject}`, httpOptions);
  }

  listarPorProjetoEIncremento(token: string, increment: number): Observable<ResponseDTO> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Project-Token': token,
      })
    }

    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.listByProjectAndIncrement}/${increment}`, httpOptions );
  }

}