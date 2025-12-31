import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { DiseaseService } from '../../services/disease.service';

@Component({
  standalone: true,
  selector: 'app-user-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-home.html',
  styleUrls: ['./user-home.css']
})
export class UserHomeComponent {

 constructor(private router: Router) {}

//   ngOnInit() {
//     this.searchDiseases();
//   }

//   searchDiseases() {
//     this.page = 0;
//     this.loadDiseases();
//   }

//   loadDiseases() {
//     this.diseaseService.searchDiseases(this.keyword, this.page, this.size)
//       .subscribe(res => {
//         this.diseases = res.content;
//         this.totalPages = res.totalPages;
//       });
//   }

//   toggleDisease(disease: any) {
//     const exists = this.selectedDiseases
//       .find(d => d.diseaseId === disease.diseaseId);

//     if (exists) {
//       this.selectedDiseases =
//         this.selectedDiseases.filter(d => d.diseaseId !== disease.diseaseId);
//     } else {
//       this.selectedDiseases.push(disease);
//     }
//   }

//   isSelected(disease: any): boolean {
//     return this.selectedDiseases
//       .some(d => d.diseaseId === disease.diseaseId);
//   }

//   nextPage() {
//     if (this.page < this.totalPages - 1) {
//       this.page++;
//       this.loadDiseases();
//     }
//   }

//   prevPage() {
//     if (this.page > 0) {
//       this.page--;
//       this.loadDiseases();
//     }
//   }

//   getRecommendations() {
//     const ids = this.selectedDiseases.map(d => d.diseaseId);

//     this.router.navigate(['/user/recommend'], {
//       state: { diseaseIds: ids, diseases: this.selectedDiseases }
//     });
//   }
    start() {
    this.router.navigate(['/user/search-diseases']);
  }
}
