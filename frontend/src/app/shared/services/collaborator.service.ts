import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';
import { CollaboratorDTO } from '../model/collaborator.dto';

const PATH = {
  contributors: '/contributors'
}

@Injectable()
export class CollaboratorService {

  constructor(private http: HttpClient) { }

  listarPorIdProjeto(id: number): Observable<ResponseDTO> {
    const httpParams = new HttpParams().set('idProject', id.toString())
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.contributors}`, { params: httpParams });
  }

  buscarPorId(id: number): Observable<ResponseDTO> {
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.contributors}/${id}`);
  }

  adicionar(collaboratorDTO: CollaboratorDTO): Observable<ResponseDTO> {

    const param: string =
        '?email=' + collaboratorDTO.user.email
        + '&idProject=' + collaboratorDTO.project.id.toString()
        + '&idRole=' + collaboratorDTO.role.id;

    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.contributors}${param}`, {});
  }

  editar(collaboratorDTO: CollaboratorDTO): Observable<ResponseDTO> {
    const param: string = '?idRole=' + collaboratorDTO.role.id;
    return this.http.put<ResponseDTO>(`${environment.urlbase}${PATH.contributors}/${collaboratorDTO.id}${param}`, {});
  }

  remover(id: number): Observable<ResponseDTO> {
    return this.http.delete<ResponseDTO>(`${environment.urlbase}${PATH.contributors}/${id}`);
  }
}