import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {
  private apiurl = 'http://localhost:9000/api/public';

  constructor(private httpClient: HttpClient) { }

  createUser(request: any): Observable<any> {
    return this.httpClient.post<any>(this.apiurl + '/signup', request).pipe(map(res => res));
  }

  private mensajeSource = new BehaviorSubject<string>('');
  mensajeActual: string='';

  cambiarMensaje(mensaje: string) {
    this.mensajeSource.next(mensaje);
  }
}
