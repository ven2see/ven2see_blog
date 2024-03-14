import { AfterViewInit, Component, DoCheck, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Post } from '../../../model/Post';
import { PostService } from '../../../service/PostService/post.service';
import { OwlOptions, CarouselComponent } from 'ngx-owl-carousel-o';
import { AuthService } from '../../../service/AuthService/Auth.service';
import { forkJoin, of, tap } from 'rxjs';
import { User } from '../../../model/User';

// ...

@Component({
  selector: 'app-home-blog',
  templateUrl: './home-blog.component.html',
  styleUrls: ['./home-blog.component.css'],
})
export class HomeBlogComponent implements OnInit{
  listPosts: Post[] = [];
  listPostsFilter: Post[]=[];
  user: User = new User();
  postId: number = 0;
  listCatego: any[] = [];

  constructor(private pService: PostService, private aService: AuthService) {}

  ngOnInit(): void {
    this.list();
    this.listCategos();
  }

  list() {
    this.aService.currentUser;
    this.pService.getPosts().subscribe((posts) => {
      this.listPosts = posts;
      this.listPostsFilter =posts;
      this.listPosts.forEach(ind=>{
        this.user = ind.user;
        console.log(this.user);
      });
    });

    
  }

  listByCatego(id: number) {
    console.log('id cate'+id);
    this.listPosts.forEach(element => {
      console.log('categoryd '+element.category);
    });
    this.listPostsFilter = this.listPosts.filter(post=> post.category === id);
    
  }

  listCategos() {
    this.pService.getCatego().subscribe((resp) => {
      if (resp) {
        this.listCatego = resp;
      }
    });
  }

  toggleFavorite(id: any) {
    this.postId = id;

    if (this.aService.currentUser) {
      this.pService
        .toggleFavorite(this.postId, this.aService.currentUser.id)
        .subscribe(
          (response) => {
            console.log(response.message);
            this.list();
          },
          (error) => {
            console.error('Error al procesar la solicitud:', error);
          }
        );
    }
  }

  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 600,
    navText: ['&#8249', '&#8250;'],
    margin: 0,
    responsive: {
      0: {
        items: 1,
      },
      400: {
        items: 2,
      },
      760: {
        items: 3,
      },
      1000: {
        items: 4,
      },
    },
    nav: true,
  };
}
