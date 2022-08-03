import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AntidoteEditComponent } from './antidote-edit.component';

describe('AntidoteEditComponent', () => {
  let component: AntidoteEditComponent;
  let fixture: ComponentFixture<AntidoteEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AntidoteEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AntidoteEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
