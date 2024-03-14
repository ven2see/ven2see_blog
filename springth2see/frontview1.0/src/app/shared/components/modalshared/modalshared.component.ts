import { Component } from '@angular/core';

import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modalshared',
  templateUrl: './modalshared.component.html',
  styleUrls: ['./modalshared.component.css']
})
export class ModalsharedComponent {
  constructor(public modalRef: MdbModalRef<ModalsharedComponent>) {}
}

// public modalRef: MdbModalRef<ModalsharedComponent>
