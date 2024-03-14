
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Post } from '../../model/Post';
import { User } from '../../model/User';

@Injectable({
providedIn: 'root'
})

export class PostService {
  private apiUrl = 'http://127.0.0.1:9000/api';

  constructor(private httpClient: HttpClient) { }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.apiUrl + '/public/posts').pipe(
      map(res => res)
    );
  }

  getCatego(): Observable<any[]>{
    return this.httpClient.get<any[]>(this.apiUrl+'/public/categories').pipe(map(res=>res));
  }

  getPostById(id:number):Observable<Post>{
    return this.httpClient.get<Post>(this.apiUrl+'/public/post/'+id);
  }

  getUserById(id:number):Observable<User>{
    return this.httpClient.get<User>(this.apiUrl+'/public/user/'+id);
  }

  isPostFavorite(postId: number, userId: number): Observable<boolean> {
    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('userId', userId.toString());

    return this.httpClient.get<boolean>(`${this.apiUrl}/user/isPostFavorite`, { params });
  }

  toggleFavorite(postId: number, userId: number): Observable<any> {
    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('userId', userId.toString());
      

    return this.httpClient.post<any>(`${this.apiUrl}/user/addFav`, {}, { params });
  }

  createPost(request: any): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl+'/user/post-create',request).pipe(map(res=>res));
  }
  

  
}
