import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AdminDiseaseService {

  private baseUrl = 'http://localhost:8080/api/admin/diseases';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.baseUrl);
  }

  create(disease: any) {
    return this.http.post(this.baseUrl, disease);
  }

  update(id: number, disease: any) {
    return this.http.put(`${this.baseUrl}/${id}`, disease);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  searchDiseases(
  keyword: string,
  page: number,
  size: number
) {
  return this.http.get<any>(
    `${this.baseUrl}/search`,
    {
      params: {
        keyword: keyword || '',
        page: page,
        size: size
      }
    }
  );
}

}
