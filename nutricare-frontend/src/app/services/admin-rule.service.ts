import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AdminRuleService {

  private baseUrl = 'http://localhost:8080/api/admin/rules';

  constructor(private http: HttpClient) {}

  addRule(foodId: number, diseaseId: number, impact: string, explanation: string) {
    const params = new HttpParams()
      .set('foodId', foodId)
      .set('diseaseId', diseaseId)
      .set('impact', impact)
      .set('explanation', explanation);

    return this.http.post(this.baseUrl, null, { params });
  }

  getRules() {
    return this.http.get<any[]>(this.baseUrl);
  }

  deleteRule(ruleId: number) {
    return this.http.delete(`${this.baseUrl}/${ruleId}`);
  }

  getFoods() {
  return this.http.get<any[]>('http://localhost:8080/api/admin/foods');
}

getDiseases() {
  return this.http.get<any[]>('http://localhost:8080/api/listAll');
}

updateRule(
  ruleId: number,
  foodId: number,
  diseaseId: number,
  impact: string,
  explanation: string
) {
  const params = new URLSearchParams({
    foodId: foodId.toString(),
    diseaseId: diseaseId.toString(),
    impact,
    explanation
  });

  return this.http.put(
    `${this.baseUrl}/${ruleId}?${params.toString()}`,
    null
  );
}

}
