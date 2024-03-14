import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../service/AuthService/Auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authservice: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authservice.getToken();

    if(token){
      const cloned = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ${token}')
    })
      return next.handle(cloned);
    }
    return next.handle(request);
  }
}
