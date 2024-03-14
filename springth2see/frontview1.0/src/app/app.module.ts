import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { AppComponent } from './app.component';
import { AuthInterceptor } from './helper/auth.interceptor';
import { PageInterceptor } from './helper/page.interceptor';
import { HomeIndexModule } from './home-index/home-index.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminPanelModule } from './admin-panel/admin-panel.module';
import { HasroleDirective } from './directive/-hasrole.directive';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    HomeIndexModule,
    RouterModule,
    NgxUiLoaderModule,
    NgbModule,
    AdminPanelModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor,multi: true}, {provide: HTTP_INTERCEPTORS, useClass: PageInterceptor,multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {

}
