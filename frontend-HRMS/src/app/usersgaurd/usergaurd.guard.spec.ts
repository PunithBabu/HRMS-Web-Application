import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { usergaurdGuard } from './usergaurd.guard';

describe('usergaurdGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => usergaurdGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
