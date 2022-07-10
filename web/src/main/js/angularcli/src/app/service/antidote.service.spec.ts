import { TestBed } from '@angular/core/testing';

import { AntidoteService } from './antidote.service';

describe('AntidoteService', () => {
  let service: AntidoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AntidoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
