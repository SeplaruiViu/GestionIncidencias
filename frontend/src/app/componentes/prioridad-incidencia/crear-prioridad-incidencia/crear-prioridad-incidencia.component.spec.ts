import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearPrioridadIncidenciaComponent } from './crear-prioridad-incidencia.component';

describe('CrearPrioridadIncidenciaComponent', () => {
  let component: CrearPrioridadIncidenciaComponent;
  let fixture: ComponentFixture<CrearPrioridadIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearPrioridadIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearPrioridadIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
