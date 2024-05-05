import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeedashboredComponent } from './employeedashbored.component';

describe('EmployeedashboredComponent', () => {
  let component: EmployeedashboredComponent;
  let fixture: ComponentFixture<EmployeedashboredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeedashboredComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeedashboredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
