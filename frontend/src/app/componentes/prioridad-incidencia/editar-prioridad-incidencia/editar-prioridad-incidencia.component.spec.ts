import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPrioridadIncidenciaComponent } from './editar-prioridad-incidencia.component';

describe('EditarPrioridadIncidenciaComponent', () => {
  let component: EditarPrioridadIncidenciaComponent;
  let fixture: ComponentFixture<EditarPrioridadIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarPrioridadIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarPrioridadIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
