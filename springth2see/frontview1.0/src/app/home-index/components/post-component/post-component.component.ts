import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from './../../../model/Post';
import { PostService } from '../../../service/PostService/post.service';
import { DatePipe } from '@angular/common';
import { User } from '../../../model/User';


@Component({
  selector: 'app-post-component',
  templateUrl: './post-component.component.html',
  styleUrls: ['./post-component.component.css'],
  providers: [DatePipe]
})
export class PostComponentComponent implements OnInit {
  
  post: Post = new Post;
  formPost: FormGroup = new FormGroup({});
  postContent: SafeHtml = '';
  autor: String ="";
  fecha = new Date(); // Puedes asignar aquí tu fecha específica.
  formattedFecha: string | null='';

  constructor(private router: Router, private route: ActivatedRoute,private pService: PostService, private sanitizer: DomSanitizer,private datePipe: DatePipe) {}
  user: User = new User();
  ngOnInit(): void {
      

   const id = this.route.snapshot.paramMap.get('id');

  this.pService.getPostById(Number(id)).subscribe(
  (data: Post) => {
    this.post = data; // Asigna la respuesta del servidor a la variable "post"
    console.log('Post recuperado:', this.post);

    // Convierte el contenido HTML en seguro y asigna a postContent
    this.postContent = this.sanitizer.bypassSecurityTrustHtml(this.post.content);
    this.user = this.post.user;
    //get and formatted date
    this.fecha = new Date(data.created_at);
    this.formattedFecha = this.datePipe.transform(this.fecha, 'dd/MM/yyyy');
  },
  function (error) {
      console.error('Error al recuperar el post:', error);
    }
);



  }

  
}


