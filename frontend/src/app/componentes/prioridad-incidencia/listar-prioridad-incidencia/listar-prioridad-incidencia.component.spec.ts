import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarPrioridadIncidenciaComponent } from './listar-prioridad-incidencia.component';

describe('ListarPrioridadIncidenciaComponent', () => {
  let component: ListarPrioridadIncidenciaComponent;
  let fixture: ComponentFixture<ListarPrioridadIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarPrioridadIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarPrioridadIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
