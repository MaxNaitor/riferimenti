import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiglioListaComponent } from './figlio-lista.component';

describe('FiglioListaComponent', () => {
  let component: FiglioListaComponent;
  let fixture: ComponentFixture<FiglioListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FiglioListaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FiglioListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
