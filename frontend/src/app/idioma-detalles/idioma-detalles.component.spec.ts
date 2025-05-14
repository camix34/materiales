import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IdiomaDetallesComponent } from './idioma-detalles.component';

describe('IdiomaDetallesComponent', () => {
  let component: IdiomaDetallesComponent;
  let fixture: ComponentFixture<IdiomaDetallesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IdiomaDetallesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IdiomaDetallesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
