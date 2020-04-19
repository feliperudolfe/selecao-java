import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProjectPaginatorDTO } from '../model/project.paginator.dto';
import { ResponseDTO } from '../model/response.dto';
import { ProjectDTO } from '../model/project.dto';

const PATH = {
  projects: '/projects'
}

@Injectable()
export class ProjectService {

  constructor(private http: HttpClient) {}

  cadastrar(projectDTO: ProjectDTO): Observable<ResponseDTO> {
    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.projects}`, projectDTO);
  }

  atualizar(projectDTO: ProjectDTO): Observable<ResponseDTO> {

    console.log('projectDTO: ', projectDTO);

    return this.http.put<ResponseDTO>(`${environment.urlbase}${PATH.projects}/${projectDTO.id}`, projectDTO);
  }

  listar(paginator: ProjectPaginatorDTO): Observable<ProjectPaginatorDTO> {

    let httpParams = new HttpParams().set('sortingColumn', 'name');
    for (const i in paginator) {
      if (i !== 'list' && paginator[i] != null) {
        httpParams = httpParams.set(i, paginator[i]);
      }
    }

    return this.http.get<ProjectPaginatorDTO>(`${environment.urlbase}${PATH.projects}`, { params: httpParams });
  }

  getProject(id: any): Observable<ResponseDTO> {
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.projects}/${id}`);
  }

  onChangeReportExecution(id: any, reportExecution: boolean): Observable<ResponseDTO> {
    const httpParams = new HttpParams().set('reportExecution', String(reportExecution));
    return this.http.put<ResponseDTO>(`${environment.urlbase}${PATH.projects}/${id}/reportExecution`, null, { params: httpParams });
  }

  remover(id: number): Observable<ResponseDTO> {
    return this.http.delete<ResponseDTO>(`${environment.urlbase}${PATH.projects}/${id}`);
  }

  abandonar(id: number): Observable<ResponseDTO> {
    return this.http.delete<ResponseDTO>(`${environment.urlbase}${PATH.projects}/${id}/abandon`);
  }
}