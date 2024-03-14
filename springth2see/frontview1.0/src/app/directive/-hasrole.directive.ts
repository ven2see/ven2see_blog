import { Directive, Input, ElementRef, Renderer2 } from '@angular/core';
import { AuthService } from '../service/AuthService/Auth.service';

@Directive({
  selector: '[hasRole]'
})
export class HasroleDirective {
  @Input('hasRole') role!: string;

  constructor(private elementRef: ElementRef, private renderer: Renderer2, private authService: AuthService) {}

  ngOnInit() {
    const roles = this.authService.currentUser.roles; // Obtener los roles del usuario desde el servicio de autenticación
    if (!roles.some(r => r.name === "ADMIN")) {
      console.log(roles);
      // Si el usuario no tiene el rol especificado, se elimina el elemento del DOM
      this.renderer.setStyle(this.elementRef.nativeElement, 'display', 'none');
    }
  }
}
