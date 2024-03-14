import { AfterViewInit, Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { UserService } from '../../service/UserService/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements AfterViewInit,OnInit{
  ListUser: User[]=[];
  constructor(private uservice: UserService){}

  ngAfterViewInit(): void {
    this.listUsers();
  }
  ngOnInit(): void {
    this.listUsers();
  }

  listUsers(){
    this.uservice.getAllUser().subscribe(resp=>{
      this.ListUser = resp;
    });
  }

}
