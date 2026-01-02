import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-admin-rules',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-rules.html',
  styleUrls: ['./admin-rules.css']
})
export class AdminRulesComponent implements OnInit {

  rules: any[] = [];
  foods: any[] = [];
  diseases: any[] = [];

  editingRuleId: number | null = null;

  foodId: number | null = null;
diseaseId: number | null = null;

  impact = 'ALLOWED';
  explanation = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.http.get<any[]>('/api/admin/rules')
      .subscribe(r => this.rules = r);

    this.http.get<any[]>('/api/admin/foods')
      .subscribe(f => this.foods = f);

    this.http.get<any[]>('/api/admin/diseases')
      .subscribe(d => this.diseases = d);
  }

  saveRule() {
  if (this.foodId === null || this.diseaseId === null) {
    alert('Please select both food and disease');
    return;
  }

  const payload = {
    foodId: this.foodId,
    diseaseId: this.diseaseId,
    impact: this.impact,
    explanation: this.explanation
  };

  this.http.post('/api/admin/rules', payload)
    .subscribe(() => {
      this.resetForm();
      this.loadAll();
    });
}


  editRule(r: any) {
    this.editingRuleId = r.ruleId;
    this.foodId = r.food.foodId;
    this.diseaseId = r.disease.diseaseId;
    this.impact = r.impact;
    this.explanation = r.explanation;
  }

  deleteRule(id: number) {
    if (confirm('Delete this rule?')) {
      this.http.delete(`/api/admin/rules/${id}`, { responseType: 'text' })
        .subscribe(() => this.loadAll());
    }
  }

  resetForm() {
    this.editingRuleId = null;
    this.foodId = null;
    this.diseaseId = null;
    this.impact = 'ALLOWED';
    this.explanation = '';
  }
}
