import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerdashboredComponent } from './managerdashbored.component';

describe('ManagerdashboredComponent', () => {
  let component: ManagerdashboredComponent;
  let fixture: ComponentFixture<ManagerdashboredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagerdashboredComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagerdashboredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
