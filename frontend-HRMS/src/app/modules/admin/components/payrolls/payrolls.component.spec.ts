import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollsComponent } from './payrolls.component';

describe('PayrollsComponent', () => {
  let component: PayrollsComponent;
  let fixture: ComponentFixture<PayrollsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PayrollsComponent]
    });
    fixture = TestBed.createComponent(PayrollsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
