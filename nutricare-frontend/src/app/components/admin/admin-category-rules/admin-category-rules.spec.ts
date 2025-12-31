import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCategoryRules } from './admin-category-rules';

describe('AdminCategoryRules', () => {
  let component: AdminCategoryRules;
  let fixture: ComponentFixture<AdminCategoryRules>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminCategoryRules]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCategoryRules);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
