import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarTipoIncidenciaComponent } from './listar-tipo-incidencia.component';

describe('ListarTipoIncidenciaComponent', () => {
  let component: ListarTipoIncidenciaComponent;
  let fixture: ComponentFixture<ListarTipoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarTipoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarTipoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
