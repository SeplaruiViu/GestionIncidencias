import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearEstadoIncidenciaComponent } from './crear-estado-incidencia.component';

describe('CrearEstadoIncidenciaComponent', () => {
  let component: CrearEstadoIncidenciaComponent;
  let fixture: ComponentFixture<CrearEstadoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearEstadoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearEstadoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
