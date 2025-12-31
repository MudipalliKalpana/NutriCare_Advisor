import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDiseases } from './admin-diseases';

describe('AdminDiseases', () => {
  let component: AdminDiseases;
  let fixture: ComponentFixture<AdminDiseases>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDiseases]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDiseases);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
