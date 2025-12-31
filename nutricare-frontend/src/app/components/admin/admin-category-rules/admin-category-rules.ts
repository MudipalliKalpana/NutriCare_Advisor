import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { HttpClient } from "@angular/common/http";

@Component({
  standalone: true,
  selector: 'app-admin-category-rules',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-category-rules.html'
})
export class AdminCategoryRulesComponent {

  category = '';
  diseaseId!: number;
  impact = 'LIMIT';
  explanation = '';

  rules: any[] = [];
  diseases: any[] = [];

  constructor(private http: HttpClient) {
    this.load();
  }

  load() {
    this.http.get<any[]>('/api/admin/category-rules')
      .subscribe(r => this.rules = r);

    this.http.get<any[]>('/api/admin/diseases')
      .subscribe(d => this.diseases = d);
  }

  add() {
    this.http.post('/api/admin/category-rules', {
      foodCategory: this.category,
      disease: { diseaseId: this.diseaseId },
      impact: this.impact,
      explanation: this.explanation
    }).subscribe(() => this.load());
  }

  delete(id: number) {
    this.http.delete('/api/admin/category-rules/' + id)
      .subscribe(() => this.load());
  }
}
