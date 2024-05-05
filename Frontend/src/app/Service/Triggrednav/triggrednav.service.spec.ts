import { TestBed } from '@angular/core/testing';

import { TriggrednavService } from './triggrednav.service';

describe('TriggrednavService', () => {
  let service: TriggrednavService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TriggrednavService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
