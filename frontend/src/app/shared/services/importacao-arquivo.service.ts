import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ResponseDTO } from '../model/response.dto';

const PATH = {
  importacao: '/importacoes'
};

@Injectable()
export class ImportacaoArquivoService {

  constructor(private http: HttpClient) {}

  importarCSV(payload): Observable<ResponseDTO> {

    const headersParam = new HttpHeaders({
      'Content-Type': 'multipart/form-data; boundary=2235235432523',
    });

    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.importacao}/carregar`, payload, { headers: headersParam, reportProgress: false, responseType: 'json' });
  }

  importarBase64CSV(payload): Observable<ResponseDTO> {
    const httpParams = new HttpParams().set('file', payload);
    return this.http.post<ResponseDTO>(`${environment.urlbase}${PATH.importacao}/carregar-base64`, null, { params: httpParams });
  }

}