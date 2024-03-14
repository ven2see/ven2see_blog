import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxUiLoaderModule } from 'ngx-ui-loader';

import { RouterModule } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ModalsharedComponent } from './components/modalshared/modalshared.component';
import { HasroleDirective } from '../directive/-hasrole.directive';



@NgModule({
  declarations: [
    HeaderComponent,
    SignUpComponent,
    ModalsharedComponent,
    HasroleDirective
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxUiLoaderModule
  ],
  exports:[
    HeaderComponent
  ]
})
export class SharedModule { }
