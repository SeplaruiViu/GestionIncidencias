import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarEstadoIncidenciaComponent } from './editar-estado-incidencia.component';

describe('EditarEstadoIncidenciaComponent', () => {
  let component: EditarEstadoIncidenciaComponent;
  let fixture: ComponentFixture<EditarEstadoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarEstadoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarEstadoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
