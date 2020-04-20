import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDTO } from '../model/response.dto';
import { UserDTO } from '../model/user.dto';

const PATH = {
  login: '/login',
  usuario: '/usuarios'
};

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {}

  doLogin(email: string, senha: string): Observable<ResponseDTO> {
    const credentials = {
      username: email,
      password: senha
    };
    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.login}`, credentials);
  }

  doLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  getUserAuth(): UserDTO {
    return JSON.parse(localStorage.getItem('user'));
  }

  register(userDTO: UserDTO): Observable<ResponseDTO> {
    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.usuario}`, userDTO);
  }

  getAuth() {
    return this.http.get<ResponseDTO>(`${environment.urlbase}${PATH.usuario}/get-dados`, {});
  }

  update(user: UserDTO) {
    return this.http.put<ResponseDTO>(`${environment.urlbase}${PATH.usuario}`, user);
  }

}