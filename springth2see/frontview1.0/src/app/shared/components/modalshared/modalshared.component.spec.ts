import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalsharedComponent } from './modalshared.component';

describe('ModalsharedComponent', () => {
  let component: ModalsharedComponent;
  let fixture: ComponentFixture<ModalsharedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalsharedComponent]
    });
    fixture = TestBed.createComponent(ModalsharedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
