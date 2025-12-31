import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiseaseService } from '../../services/disease.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-disease-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './disease-search.html',
  styleUrl: './disease-search.css'
})
export class DiseaseSearchComponent implements OnInit {

  diseases: any[] = [];
  selectedDiseaseIds: number[] = [];

  keyword = '';
  page = 0;

  constructor(
    private diseaseService: DiseaseService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadDiseases();
  }

  loadDiseases() {
    this.diseaseService.searchDiseases(this.keyword, this.page)
      .subscribe(res => {
        this.diseases = res.content;
      });
  }

  toggleSelection(diseaseId: number) {
    if (this.selectedDiseaseIds.includes(diseaseId)) {
      this.selectedDiseaseIds =
        this.selectedDiseaseIds.filter(id => id !== diseaseId);
    } else {
      this.selectedDiseaseIds.push(diseaseId);
    }
  }

  getRecommendations() {
    this.router.navigate(['/user/recommend'], {
      state: {
        diseaseIds: this.selectedDiseaseIds
      }
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
