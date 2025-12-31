import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRulesComponent } from './admin-rules';

describe('AdminRules', () => {
  let component: AdminRulesComponent;
  let fixture: ComponentFixture<AdminRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminRulesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
