import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { HomeIndexCComponent } from './components/Home-indexC/Home-indexC.component';
import { HomeBlogComponent } from './components/home-blog/home-blog.component';
import { HomeIComponent } from './components/home-i/home-i.component';
import { HomeShopComponent } from './components/home-shop/home-shop.component';
import { PostComponentComponent } from './components/post-component/post-component.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { DatePipe } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { SingleProductComponent } from './components/Single-product/single-product/single-product.component';



@NgModule({
  declarations: [
    HomeIndexCComponent,
    HomeBlogComponent,
    HomeIComponent,
    HomeShopComponent,
    PostComponentComponent,
    SingleProductComponent,
    ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    CarouselModule,
    DatePipe,
    NgxPaginationModule
  ],
  exports:[
    HomeIndexCComponent,
    HomeBlogComponent,
    HomeIComponent,
    HomeShopComponent,
    PostComponentComponent,
    
  ]
})
export class HomeIndexModule { }
