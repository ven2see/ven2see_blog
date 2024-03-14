import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';


import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Observable, finalize } from 'rxjs';

@Injectable()
export class PageInterceptor implements HttpInterceptor {

  constructor(private loaderService: NgxUiLoaderService) {}
  private activeRequest =0;

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    //Loader
    if(this.activeRequest===0){
    this.loaderService.start();
    }

    this.activeRequest++;
    return next.handle(request).pipe(finalize(()=>this.stopLoader()));
  }

  private stopLoader(){
    this.activeRequest--;
    if(this.activeRequest===0){
      this.loaderService.stop();
    }
  }
}
