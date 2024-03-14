import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuNavComponent } from './menu-nav/menu-nav.component';
import { SharedModule } from '../shared/shared.module';
import { UsersComponent } from './users/users.component';
import { PostComponent } from './post/post.component';
import { ProductComponent } from './product/product.component';



@NgModule({
  declarations: [
    MenuNavComponent,
    UsersComponent,
    PostComponent,
    ProductComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule
  ],
  exports:[
    RouterModule
  ]
})
export class AdminPanelModule { }
