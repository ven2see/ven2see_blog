import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Credentials, User } from '../../model/User';
import { jwtDecode } from 'jwt-decode';
import { RouterTestingHarness } from '@angular/router/testing';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9000';
  public currentUser: User = new User();

  constructor(private http: HttpClient) { }

  // Método para iniciar sesión
  login(creds: Credentials) {

    return this.http.post(this.apiUrl+'/api/login', creds, {
      observe: 'response'
    }).pipe(map((response: HttpResponse<any>) => {
          const body = response.body;
          const headers = response.headers;

          const bearerToken = headers.get('Authorization')!;

          const token = bearerToken.replace('Bearer ', '');

          localStorage.setItem('token', token);
          return body;
      }))

  }

  getToken() {
    return localStorage.getItem('token');
  }


getUserFromToken()  {
  const token = this.getToken();

  if (token) {
    try {
      // Decodificar el token
      const decodedToken: any = jwtDecode(token);
      const data = decodedToken.userInfo.user;
      const roles = decodedToken.roles;

      const user: User={
        id: data.id,
        name: data.name,
        lastname: data.lastname,
        username: data.username,
        email: data.email,
        status: data.status,
        roles: data.roles,
        password: '',
        image: '',
        product: data.marketplace
      };

      // Devolver la información del usuario
      if(user!=null){
        this.currentUser = user;
      }
      return {user,roles};
    } catch (error) {
      console.error('Error decodificando el token:', error);
      return null;
    }
  }

  return null; // No hay token disponible
}

   logout() {
    return localStorage.removeItem('token');
  }
}
