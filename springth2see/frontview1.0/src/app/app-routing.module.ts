import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { HomeIndexCComponent } from './home-index/components/Home-indexC/Home-indexC.component';
import { PostComponentComponent } from './home-index/components/post-component/post-component.component';
import { SignUpComponent } from './shared/sign-up/sign-up.component';
import { MenuNavComponent } from './admin-panel/menu-nav/menu-nav.component';
import { UsersComponent } from './admin-panel/users/users.component';
import { PostComponent } from './admin-panel/post/post.component';
import { SingleProductComponent } from './home-index/components/Single-product/single-product/single-product.component';



const routes: Routes = [
  { path: '', component: HomeIndexCComponent},
   { path: 'post/:id', component: PostComponentComponent},
   {path: 'sign-up', component: SignUpComponent},
   {path: 'single-product/:id', component: SingleProductComponent},
   //{ path: 'app-control', loadChildren: () => import('./admin-panel/admin-panel.module').then(m => m.AdminPanelModule) }
   { path: 'app-control', 
   component: MenuNavComponent,
  children:[
    {path: 'users', component: UsersComponent},
    {path: 'posts',component: PostComponent}
  ] }
  //  { path: 'post/:id', component: PostComponentComponent,canActivate:[authGuard]},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
