import { TestBed } from '@angular/core/testing';

import { PageInterceptor } from './page.interceptor';

describe('PageInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      PageInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: PageInterceptor = TestBed.inject(PageInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
