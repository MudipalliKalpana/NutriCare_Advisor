import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCategoryRulesComponent } from './admin-category-rules';

describe('AdminCategoryRules', () => {
  let component: AdminCategoryRulesComponent;
  let fixture: ComponentFixture<AdminCategoryRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminCategoryRulesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCategoryRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
