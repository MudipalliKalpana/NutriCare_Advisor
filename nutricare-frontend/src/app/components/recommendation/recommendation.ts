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

  formatExplanation(text: string): string {
  if (!text) return '';

  return text
    .split(';')
    .map(line => `• ${line.trim()}`)
    .join('<br>');
}


extractBenefits(text: string): string[] {
  const match = text?.match(/Benefits:(.*?)(Warnings:|$)/);
  return match
    ? match[1].split(',').map(b => b.trim())
    : [];
}

extractWarnings(text: string): string[] {
  const match = text?.match(/Warnings:(.*)$/);
  return match
    ? match[1].split(',').map(w => w.trim())
    : [];
}
  
  goBack(){
    this.router.navigate(['/user/search-diseases']);
  }
}
