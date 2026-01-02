import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecommendationService } from '../../services/recommendation.services';
import { Router } from '@angular/router';
@Component({
  standalone: true,
  selector: 'app-recommendation',
  imports: [CommonModule],
  templateUrl: './recommendation.html',
  styleUrls: ['./recommendation.css']
})
export class RecommendationComponent implements OnInit {

  recommendations: any[] = [];
  selectedDiseases: any[] = [];
  diseaseIds: number[] = [];

  constructor(private recommendationService: RecommendationService,
            private router:Router
  ) {}

  ngOnInit() {
    const navState = history.state;

    if (!navState || !navState.diseaseIds || navState.diseaseIds.length === 0) {
      console.warn('No diseases received for recommendation');
      return;
    }

    this.diseaseIds = navState.diseaseIds;
    this.selectedDiseases = navState.diseases || [];

    this.loadRecommendations();
  }

  loadRecommendations() {
    this.recommendationService
      .getRecommendations(this.diseaseIds)
      .subscribe({
        next: (data) => {
          this.recommendations = data;
        },
        error: (err) => {
          console.error('Failed to load recommendations', err);
        }
      });
  }

  formatExplanation(text: string): string[] {
  if (!text) return [];

  const lines = text
    .split(';')
    .map(line => line.trim())
    .filter(line => line.length > 0);

  return Array.from(
    new Set(
      lines.filter(line =>
        !line.toLowerCase().startsWith('benefits') &&
        !line.toLowerCase().startsWith('warnings')
      )
    )
  );
}


extractBenefits(text: string): string[] {
  if (!text) return [];

  return Array.from(
    new Set(
      text
        .split(';')
        .map(line => line.trim())
        .filter(line => line.toLowerCase().startsWith('benefits'))
        .map(b => b.replace(/^Benefits:/i, '').trim())
    )
  );
}

extractWarnings(text: string): string[] {
  if (!text) return [];

  return Array.from(
    new Set(
      text
        .split(';')
        .map(line => line.trim())
        .filter(line => line.toLowerCase().startsWith('warnings'))
        .map(w => w.replace(/^Warnings:/i, '').trim())
    )
  );
}
  
  goBack(){
    this.router.navigate(['/user/search-diseases']);
  }
}
