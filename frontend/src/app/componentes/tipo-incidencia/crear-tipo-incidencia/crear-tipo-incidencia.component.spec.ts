import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearTipoIncidenciaComponent } from './crear-tipo-incidencia.component';

describe('CrearTipoIncidenciaComponent', () => {
  let component: CrearTipoIncidenciaComponent;
  let fixture: ComponentFixture<CrearTipoIncidenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearTipoIncidenciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearTipoIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
