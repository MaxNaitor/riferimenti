import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzazioneComponent } from './visualizzazione.component';

describe('VisualizzazioneComponent', () => {
  let component: VisualizzazioneComponent;
  let fixture: ComponentFixture<VisualizzazioneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzazioneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
