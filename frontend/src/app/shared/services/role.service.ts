import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';

const PATH = {
  roles: '/roles'
}

@Injectable()
export class RoleService {

  constructor(private http: HttpClient) {}

  listar(): Observable<ResponseDTO> {
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.roles}`);
  }
}