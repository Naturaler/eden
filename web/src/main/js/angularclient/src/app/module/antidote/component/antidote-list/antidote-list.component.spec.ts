import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AntidoteListComponent } from './antidote-list.component';

describe('AntidoteListComponent', () => {
  let component: AntidoteListComponent;
  let fixture: ComponentFixture<AntidoteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AntidoteListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AntidoteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
