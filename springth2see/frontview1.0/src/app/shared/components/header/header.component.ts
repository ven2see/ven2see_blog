import { Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from '../../../service/PostService/post.service';

import { AuthService } from './../../../service/AuthService/Auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalsharedComponent } from '../modalshared/modalshared.component';
import { Credentials, User } from '../../../model/User';
import { SignUpService } from '../../../service/SUPService/signUp.service';
import { MarketplaceService } from '../../../service/MarketplaceService/Market.service';
import { Role } from '../../../model/Role';
declare var tinymce: any; 

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [SignUpService, MdbModalService, AuthService],
})
export class HeaderComponent implements AfterViewInit ,OnInit, DoCheck{
  
    
  modalRef: MdbModalRef<ModalsharedComponent> | null = null;

  creds: Credentials = { username: '', password: '' };
  errorMessage: string = '';
  regisMessage: string = '';
  formPost: FormGroup = new FormGroup({});
  formData = new FormData();
  currentUser: User | null = null;
  options: any[]=[];
  selectedCategoryId: number | null = 1;
  cantItems: number = 0;
  

  constructor(
    public authService: AuthService,
    private http: HttpClient,
    private router: Router,
    private location: Location,
    private pService: PostService,
    private SUService: SignUpService,
    private modalService: MdbModalService,
    private sService:MarketplaceService
  ) {}
  ngDoCheck(): void {
     this.cantItems= this.sService.itemsSave.length;
  }
  ngOnInit() {
    
    this.authService.getUserFromToken();
    console.log(this.getAuthority());
    this.formPost = new FormGroup({
      id: new FormControl(1),
      title: new FormControl(''),
      brief: new FormControl(''),
      content: new FormControl(''),
      user_id: new FormControl(''),
      category: new FormControl('')
    });
  }
  ngAfterViewInit() {
    const {data}=this.sService.loadShopBag();
    this.sService.itemsSave = data;
    console.log(this.regisMessage);
    tinymce.init({
      selector: 'textarea',
      height: 300,
      plugins: 'image code',
      toolbar: 'undo redo | link image | code',
      automatic_uploads: true,
      setup: (editor: any) => {
        editor.on('input', () => {
          const contentValue = editor.getContent();
          if (this.formPost) {
            this.formPost.patchValue({
              content: contentValue,
            });
          }
        });
      },
      file_picker_types: 'image',
      file_picker_callback: (cb:any, value:any, meta:any) => {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
      
        input.addEventListener('change', (e) => {
          if (e.target instanceof HTMLInputElement) {
            const fileInput = e.target;
            const files = fileInput.files;
      
            if (files && files.length > 0) {
              const file = files[0];
      
              const reader = new FileReader();
              reader.addEventListener('load', () => {
                // Verificar si reader.result es null
                if (reader.result !== null && typeof reader.result === 'string') {
                  const id = 'blobid' + (new Date()).getTime();
                  const blobCache = tinymce.activeEditor.editorUpload.blobCache;
                  const base64 = reader.result.split(',')[1];
                  const blobInfo = blobCache.create(id, file, base64);
                  blobCache.add(blobInfo);
      
                  // Llamar al callback y proporcionar la URI de la imagen
                  cb(blobInfo.blobUri(), { title: file.name });


                }
              });
      
              reader.readAsDataURL(file);
            }
          }
        });
      
        input.click();
      },
        
      content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
    });
  
  
    window.addEventListener('scroll', () => {
      const header = document.querySelector('header');
      if (header) {
        header.classList.toggle('sticky', window.scrollY > 0);
      }
    });

    document.addEventListener('click', (event) => {
      const target = event.target as HTMLElement;
      if (
        target &&
        target.matches('header nav div div') &&
        target.parentElement != null
      ) {
        target.classList.add('active');
        const siblings = Array.from(target.parentElement.children).filter(
          (el) => el !== target
        );
        siblings.forEach((sibling) => sibling.classList.remove('active'));
      }
    });

    this.getCatego();
    this.authService.currentUser;
    
    console.log(this.authService.currentUser.id);
  }

  login(form: NgForm) {
    console.log('form value', form.value);
    
    this.authService.login(form.value).subscribe(
      (response) => {
        this.dismissModalById('exampleModal2');
      },
      (error) => {
        // En caso de error en la autenticación, muestra un mensaje de error
        this.errorMessage = 'Username or Password incorrect';
        console.log(error);
      }
    );
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.formData.append('fichero', file);

    this.formPost.patchValue({
      user_id: this.authService.currentUser.id,
    });

    console.log(this.authService.currentUser.id);
  }

  onCategorySelected(value:string) {
    // Actualizar la propiedad category_id del formulario
    this.selectedCategoryId=+value;
    this.formPost.patchValue({
      category: this.selectedCategoryId
    });

    
   
  }
  

  save() {
    this.formPost.patchValue({
      user_id: this.authService.currentUser.id,
    });

    const formPostJson = JSON.stringify(this.formPost.value);
    this.formData.append('post', formPostJson);
    console.log(this.formData);
    console.log(formPostJson);

    this.pService.createPost(this.formData).subscribe((resp) => {
      if (resp) {
        console.log('exito');
        this.dismissModalById('exampleModal3');
      }
    });
  }

  getCatego(){
    this.pService.getCatego().subscribe(resp=>{
      if(resp){
      this.options = resp;
      }
    });
  }

  newPost() {
    this.formPost.reset();
    
    console.log(this.authService.getUserFromToken());
  }

  dismissModalById(modalId: string): void {
    const modal = document.getElementById(modalId);
    if (modal) {
      modal.classList.remove('show');
      modal.style.display = 'none';
      const modalBackdrop =
        document.getElementsByClassName('modal-backdrop')[0];
      if (modalBackdrop) {
        if (modalBackdrop.parentNode != null) {
          modalBackdrop.parentNode.removeChild(modalBackdrop);
        }
      }
      document.body.classList.remove('modal-open');
    }
  }
  getAuthority(): number{
    let value = 0;
    if(this.authService.currentUser.roles.some(role=>role.id===1)){
      value = 1;
    }else if(this.authService.currentUser.roles.some(role=>role.id===2)){
    value=2;
    }else if(this.authService.currentUser.roles.some(role=>role.id===3)){
      value=3;
    }

    return value;
  }
  openModal() {
    this.modalRef = this.modalService.open(ModalsharedComponent);
  }

  logout(){
    this.authService.logout();
  }

  
}
