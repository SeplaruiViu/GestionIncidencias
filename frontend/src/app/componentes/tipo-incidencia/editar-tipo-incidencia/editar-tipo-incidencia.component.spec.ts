import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarTipoIncidenciaComponent } from './editar-tipo-incidencia.component';

describe('EditarTipoIncidenciaComponent', () => {
  let component: EditarTipoIncidenciaComponent;
  let fixture: ComponentFixture<EditarTipoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarTipoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarTipoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
