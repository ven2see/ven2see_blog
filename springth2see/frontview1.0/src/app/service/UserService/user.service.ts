import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../model/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9000/api/app-control/';
  constructor(private http: HttpClient) { }

  getAllUser(): Observable<User[]>{
    return this.http.get<User[]>(this.apiUrl+'users');
  }
}
