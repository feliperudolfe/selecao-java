import { Injectable } from '@angular/core';
import {
    HttpInterceptor,
    HttpRequest,
    HttpResponse,
    HttpHandler,
    HttpEvent,
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';
import { MsgService } from '../services/msg.service';
import { ResponseDTO } from '../model/response.dto';
import { TranslateService } from '@ngx-translate/core';

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {

    constructor(private spinner: NgxSpinnerService, private msg: MsgService, private translate: TranslateService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        this.spinner.show();

        const token: string = localStorage.getItem('token');
        if (token) {
            request = request.clone({ headers: request.headers.set('Authorization', 'Basic ' + token) });
        }

        if (!request.headers.has('Content-Type')) {
            request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
        }

        if (this.translate.currentLang) {
            request = request.clone({ headers: request.headers.set('Accept-Language', this.translate.currentLang) });
        }

        request = request.clone({ headers: request.headers.set('Accept', 'application/json') });

        return next.handle(request).pipe(map((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
                this.spinner.hide();
            }
            return event;
        }), catchError((error) => {
            if (error['error']) {
                const response: ResponseDTO = error['error'];
                if (response && response.messages) {
                    this.msg.show(response.messages[0]);
                }
            } else {
                this.msg.show({type: 'error', text: 'Ocorreu um erro ao executar esta operação'});
            }

            this.spinner.hide();
            return this.handlerError(error);
        }));
    }

    private handlerError(error: any) {
        return throwError(error);
    }

}