import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environments";

@Injectable({
    providedIn: 'root'
})

export class RecommendationService {
    private baseUrl = environment.apiBaseUrl + '/api/recommend';

    constructor(private http: HttpClient) { }

    getRecommendations(diseaseIds: number[]) {
        return this.http.post<any[]>(
            this.baseUrl,
            diseaseIds
        );
    }

}