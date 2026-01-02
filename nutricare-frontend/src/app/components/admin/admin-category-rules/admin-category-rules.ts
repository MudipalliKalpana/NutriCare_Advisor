import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { HttpClient } from "@angular/common/http";

@Component({
  standalone: true,
  selector: 'app-admin-category-rules',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-category-rules.html',
  styleUrl: './admin-category-rules.css'
})
export class AdminCategoryRulesComponent {

  category = '';
  diseaseId: number | null = null;
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

    // 🔴 Correct endpoint
    this.http.get<any>('/api/diseases/search', {
  params: {
    keyword: '',
    size: '100'
  }
}).subscribe(res => {
  this.diseases = res.content;
});

  }

  add() {
    if (!this.category || !this.diseaseId) {
      alert('Please fill all required fields');
      return;
    }

    this.http.post('/api/admin/category-rules', {
      foodCategory: this.category.trim(),
      disease: { diseaseId: this.diseaseId },
      impact: this.impact,
      explanation: this.explanation?.trim()
    }).subscribe(() => {
      this.category = '';
      this.explanation = '';
      this.impact = 'LIMIT';
      this.load();
    });
  }

  delete(id: number) {
    this.http.delete('/api/admin/category-rules/' + id)
      .subscribe(() => this.load());
  }
}
