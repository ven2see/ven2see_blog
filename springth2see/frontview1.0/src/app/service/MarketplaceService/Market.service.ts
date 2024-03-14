import { Injectable } from '@angular/core';
import { Marketplace } from '../../model/Marketplace';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MarketplaceService {
  itemsSave: Marketplace[]=[];
  id: number=0;
  
  private apiUrl = 'http://127.0.0.1:9000/api/public';

  constructor(private httpClient: HttpClient, private route:ActivatedRoute){ }

  saveBag(){
    localStorage.setItem('shopBag', JSON.stringify(this.itemsSave));
  }

  getAllProduct(): Observable<Marketplace[]>{
    return this.httpClient.get<Marketplace[]>(this.apiUrl +'/products').pipe(map(res => res));
  }
 
  getProductById(id:number):Observable<Marketplace>{
    return this.httpClient.get<Marketplace>(this.apiUrl+'/single-product/'+id);
  }
  
  loadShopBag():{data: Marketplace[],size:number}{
    const data = localStorage.getItem('shopBag');
    const dataArray = data ? JSON.parse(data):[];
    return {data:dataArray, size:dataArray.length};
  }

}
