import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SignUpService } from './../../service/SUPService/signUp.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MdbModalService } from 'mdb-angular-ui-kit/modal';
import { HeaderComponent } from '../components/header/header.component';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
  providers:[SignUpService,HeaderComponent,MdbModalService]
})
export class SignUpComponent {

  response: string = '';
  usernameExits:Boolean =false;
  formUser: FormGroup = new FormGroup({});

  constructor(private SUService: SignUpService, private router: Router,private modalService: NgbModal, private headercomponent: HeaderComponent) { }

  ngOnInit() {
    this.formUser = new FormGroup({
      id: new FormControl(''),
      name: new FormControl(''),
      lastname: new FormControl(''),
      username: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl(''),
      image: new FormControl(''),
      status: new FormControl(''),
    });
    this.usernameExits = false;
  }

  register() {
    this.SUService.createUser(this.formUser.value).subscribe(
      (res) => {
        if (res) {
          this.response = 'Your account has been created successfully!';
          this.SUService.mensajeActual = this.response;
          this.usernameExits = false;
          this.router.navigate(['/']);
          this.headercomponent.openModal();
        } else{
          console.log(1);
        }
      },
      (error) => {
        if (error.status === 409) {
          this.usernameExits = true;
          this.response = 'The username already exists, try again.';
        } else {
          console.error('Error during registration:', error);
        }
      }
    );
  }
  
  
usernameValid(){
  return this.usernameExits;
}


}
