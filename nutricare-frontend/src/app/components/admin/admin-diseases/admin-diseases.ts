import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminDiseaseService } from '../../../services/admin-disease.service';

@Component({
  selector: 'app-admin-diseases',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-diseases.html',
  styleUrl: './admin-diseases.css'
})
export class AdminDiseasesComponent implements OnInit {

  diseases: any[] = [];

  // form model
  diseaseId?: number;
  diseaseName = '';
  description = '';
  isActive = true;

  // search & pagination
  keyword = '';
  page = 0;
  size = 5;
  totalPages = 0;

  constructor(private diseaseService: AdminDiseaseService) { }

  ngOnInit() {
    this.loadDiseases();
  }

  loadDiseases() {
    this.diseaseService
      .searchDiseases(this.keyword, this.page, this.size)
      .subscribe(res => {
        this.diseases = res.content;
        this.totalPages = res.totalPages;
      });
  }

  onSearch() {
    this.page = 0;
    this.loadDiseases();
  }

  next() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadDiseases();
    }
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
      description: this.description,
      isActive: this.isActive
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
    this.isActive = d.isActive;
  }

  deleteDisease(id: number) {
    if (confirm('Delete this disease?')) {
      this.diseaseService.delete(id).subscribe(() => {
        this.diseases = this.diseases.filter(d => d.diseaseId !== id);
      });
    }
  }


  resetForm() {
    this.diseaseId = undefined;
    this.diseaseName = '';
    this.description = '';
    this.isActive = true;
  }
}
