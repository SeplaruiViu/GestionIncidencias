import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarTecnicosComponent } from './editar-tecnicos.component';

describe('EditarTecnicosComponent', () => {
  let component: EditarTecnicosComponent;
  let fixture: ComponentFixture<EditarTecnicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarTecnicosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarTecnicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
