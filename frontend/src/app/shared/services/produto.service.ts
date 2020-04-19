import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OptionDTO } from '../model/options.dto';

const PATH = {
  produto: '/produtos'
};

@Injectable()
export class ProdutoService {

  constructor(private http: HttpClient) {}

  listarOptions(): Observable<Array<OptionDTO>> {
    return this.http.get<Array<OptionDTO>>(`${environment.urlbase}${PATH.produto}/options`);
  }

}