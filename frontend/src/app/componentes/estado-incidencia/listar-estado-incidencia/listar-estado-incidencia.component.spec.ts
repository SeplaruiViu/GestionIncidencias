import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarEstadoIncidenciaComponent } from './listar-estado-incidencia.component';

describe('ListarEstadoIncidenciaComponent', () => {
  let component: ListarEstadoIncidenciaComponent;
  let fixture: ComponentFixture<ListarEstadoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarEstadoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarEstadoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
