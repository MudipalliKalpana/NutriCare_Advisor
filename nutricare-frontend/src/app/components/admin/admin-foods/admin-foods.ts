import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminFoodService } from '../../../services/admin-food.service';

@Component({
    selector: 'app-admin-foods',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './admin-foods.html',
    styleUrls: ['./admin-foods.css']
})
export class AdminFoodsComponent implements OnInit {

  foods: any[] = [];

  foodName = '';
  category = '';
  portionSize = '';

  editingFoodId: number | null = null;

  constructor(private foodService: AdminFoodService) {}

  ngOnInit() {
    this.loadFoods();
  }

  loadFoods() {
    this.foodService.getFoods().subscribe(data => {
      this.foods = data;
    });
  }

  addFood() {
    const payload = {
      foodName: this.foodName.trim(),
      category: this.category.trim(),
      portionSize: this.portionSize.trim(),
      isActive: true
    };

    this.foodService.addFood(payload).subscribe(() => {
      this.resetForm();
      this.loadFoods();
    });
  }

  editFood(food: any) {
    this.editingFoodId = food.foodId;
    this.foodName = food.foodName;
    this.category = food.category;
    this.portionSize = food.portionSize;
  }

  saveEdit() {
    if (this.editingFoodId === null) return;

    const payload = {
      foodId: this.editingFoodId,
      foodName: this.foodName.trim(),
      category: this.category.trim(),
      portionSize: this.portionSize.trim(),
      isActive: true
    };

    this.foodService.updateFood(this.editingFoodId, payload).subscribe(() => {
      this.resetForm();
      this.loadFoods();
    });
  }

  toggleActive(food: any) {
    const payload = {
      ...food,
      isActive: !food.isActive
    };

    this.foodService.updateFood(food.foodId, payload).subscribe(() => {
      this.loadFoods();
    });
  }

  resetForm() {
    this.foodName = '';
    this.category = '';
    this.portionSize = '';
    this.editingFoodId = null;
  }
}
