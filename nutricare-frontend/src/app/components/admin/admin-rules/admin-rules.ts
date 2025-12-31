import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRuleService } from '../../../services/admin-rule.service';

@Component({
  standalone: true,
  selector: 'app-admin-rules',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-rules.html'
})
export class AdminRulesComponent implements OnInit {

  foodId!: number;
  diseaseId!: number;
  impact = 'ALLOWED';
  explanation = '';

  rules: any[] = [];

  constructor(private ruleService: AdminRuleService) { }


  addRule() {
    this.ruleService.addRule(
      this.foodId,
      this.diseaseId,
      this.impact,
      this.explanation
    ).subscribe(() => {
      this.loadRules();
      this.explanation = '';
    });
  }

  loadRules() {
    this.ruleService.getRules().subscribe(data => {
      this.rules = data;
    });
  }

  deleteRule(id: number) {
    this.ruleService.deleteRule(id).subscribe(() => {
      this.loadRules();
    });
  }

  foods: any[] = [];
  diseases: any[] = [];
  editingRuleId: number | null = null;

  ngOnInit() {
    this.loadRules();
    this.loadFoods();
    this.loadDiseases();
  }

  cancelEdit() {
  this.editingRuleId = null;
  this.foodId = null!;
  this.diseaseId = null!;
  this.impact = 'ALLOWED';
  this.explanation = '';
}

  loadFoods() {
    this.ruleService.getFoods().subscribe(data => this.foods = data);
  }

  loadDiseases() {
    this.ruleService.getDiseases().subscribe(data => this.diseases = data);
  }

  editRule(rule: any) {
    this.editingRuleId = rule.ruleId;
    this.foodId = rule.food.foodId;
    this.diseaseId = rule.disease.diseaseId;
    this.impact = rule.impact;
    this.explanation = rule.explanation;
  }

  saveEdit() {
    if (this.editingRuleId === null) return;

    this.ruleService.updateRule(
      this.editingRuleId,
      this.foodId,
      this.diseaseId,
      this.impact,
      this.explanation
    ).subscribe(() => {
      this.editingRuleId = null;
      this.loadRules();
    });
  }


}
