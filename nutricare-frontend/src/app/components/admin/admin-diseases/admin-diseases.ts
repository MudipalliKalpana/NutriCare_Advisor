import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminDiseaseService } from '../../../services/admin-disease.service';

@Component({
  selector: 'app-admin-diseases',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-diseases.html'
})
export class AdminDiseasesComponent implements OnInit {

  diseases: any[] = [];

  // form model
  diseaseId?: number;
  diseaseName = '';
  description = '';

  constructor(private diseaseService: AdminDiseaseService) {}

  ngOnInit() {
    this.loadDiseases();
  }

  keyword = '';
page = 0;
size = 5;

loadDiseases() {
  this.diseaseService
    .searchDiseases(this.keyword, this.page, this.size)
    .subscribe(res => {
      this.diseases = res.content;
    });
}

next() {
  this.page++;
  this.loadDiseases();
}

prev() {
  if (this.page > 0) {
    this.page--;
    this.loadDiseases();
  }
}


  saveDisease() {
    const payload = {
      diseaseName: this.diseaseName,
      description: this.description
    };

    if (this.diseaseId) {
      this.diseaseService.update(this.diseaseId, payload)
        .subscribe(() => {
          this.resetForm();
          this.loadDiseases();
        });
    } else {
      this.diseaseService.create(payload)
        .subscribe(() => {
          this.resetForm();
          this.loadDiseases();
        });
    }
  }

  editDisease(d: any) {
    this.diseaseId = d.diseaseId;
    this.diseaseName = d.diseaseName;
    this.description = d.description;
  }

  deleteDisease(id: number) {
    if (confirm('Delete this disease?')) {
      this.diseaseService.delete(id)
        .subscribe(() => this.loadDiseases());
    }
  }

  resetForm() {
    this.diseaseId = undefined;
    this.diseaseName = '';
    this.description = '';
  }
}
