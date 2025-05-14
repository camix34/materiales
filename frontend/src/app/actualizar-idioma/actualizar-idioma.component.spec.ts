import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarIdiomaComponent } from './actualizar-idioma.component';

describe('ActualizarIdiomaComponent', () => {
  let component: ActualizarIdiomaComponent;
  let fixture: ComponentFixture<ActualizarIdiomaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ActualizarIdiomaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActualizarIdiomaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
