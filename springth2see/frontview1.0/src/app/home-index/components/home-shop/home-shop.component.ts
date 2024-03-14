import { Component, Input, OnInit } from '@angular/core';
import { MarketplaceService } from '../../../service/MarketplaceService/Market.service';
import { Marketplace } from '../../../model/Marketplace';
import { User } from '../../../model/User';
@Component({
  selector: 'app-home-shop',
  templateUrl: './home-shop.component.html',
  styleUrls: ['./home-shop.component.css']
})
export class HomeShopComponent implements OnInit {
  @Input() title: string = 'Product';
  products: Marketplace[]=[];
  seller: User|null=null;
  p: number = 1;
  items: number[] = []; 
  
  constructor(private sService:MarketplaceService){}
  ngOnInit(): void {
    this.fillProductList();
  }
  
  fillItems(){
    for(let i=0,y=1; i<=this.products.length; i+=4,y++){
      this.items.push(y);
    }
  }

  addItems(shop:Marketplace){
    this.sService.itemsSave.push(shop);
    this.sService.saveBag();
    
   console.log(this.sService.itemsSave);
  }

  fillProductList(){
    this.sService.getAllProduct().subscribe(resp=>{
      this.products = resp;
      resp.forEach(index=>{
        this.seller = index.seller;
      });
      this.fillItems();
    });
  }

}
