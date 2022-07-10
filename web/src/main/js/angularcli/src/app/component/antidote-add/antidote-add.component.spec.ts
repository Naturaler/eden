import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AntidoteAddComponent } from './antidote-add.component';

describe('AntidoteAddComponent', () => {
  let component: AntidoteAddComponent;
  let fixture: ComponentFixture<AntidoteAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AntidoteAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AntidoteAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
