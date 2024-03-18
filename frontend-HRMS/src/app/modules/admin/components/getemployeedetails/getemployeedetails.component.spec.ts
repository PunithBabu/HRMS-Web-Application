import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetemployeedetailsComponent } from './getemployeedetails.component';

describe('GetemployeedetailsComponent', () => {
  let component: GetemployeedetailsComponent;
  let fixture: ComponentFixture<GetemployeedetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetemployeedetailsComponent]
    });
    fixture = TestBed.createComponent(GetemployeedetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
