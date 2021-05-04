import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsercizioDirettiveComponent } from './esercizio-direttive.component';

describe('EsercizioDirettiveComponent', () => {
  let component: EsercizioDirettiveComponent;
  let fixture: ComponentFixture<EsercizioDirettiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EsercizioDirettiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EsercizioDirettiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
