import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Marketplace } from '../../../../model/Marketplace';
import { ActivatedRoute } from '@angular/router';
import { MarketplaceService } from '../../../../service/MarketplaceService/Market.service';
import { User } from '../../../../model/User';

@Component({
  selector: 'app-single-product',
  templateUrl: './single-product.component.html',
  styleUrl: './single-product.component.css'
})
export class SingleProductComponent implements OnInit{
  product: Marketplace = new Marketplace();
  id:number=0;
  seller: User|null=null;
  constructor(private sService: MarketplaceService,private route: ActivatedRoute){}

  ngOnInit(): void {
    this.loadPost();
    console.log(this.loadPost());
  }

  loadPost(){
    this.route.params.subscribe(params=>{
      this.id = params['id'];
    });

    this.sService.getProductById(this.id).subscribe(resp=>{
      this.seller = resp.seller;
      this.product = resp;
    });
  }


}
