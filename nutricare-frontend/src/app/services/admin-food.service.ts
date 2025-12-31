import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environments';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdminFoodService {

  private baseUrl = `${environment.apiBaseUrl}/api/admin/foods`;

  constructor(private http: HttpClient) {}

  getFoods(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  addFood(food: any) {
    return this.http.post(this.baseUrl, food);
  }

  updateFood(id: number, food: any) {
    return this.http.put(`${this.baseUrl}/${id}`, food);
  }

  toggleFood(food: any) {
  const updatedFood = {
    ...food,
    isActive: !food.isActive
  };

  return this.http.put(`${this.baseUrl}/${food.foodId}`, updatedFood);
}

}
