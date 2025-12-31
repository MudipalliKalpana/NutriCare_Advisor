// import { Injectable } from "@angular/core";
// import { HttpClient } from "@angular/common/http";
// import { environment } from "../../environments/environments";

// @Injectable({
//     providedIn:'root'
// })

// export class DiseaseService{
//     private baseUrl=environment.apiBaseUrl+'/api/admin/diseases';

//     constructor(private http:HttpClient){}

//     searchDiseases(keyword='',page=0,size=10){
//         return this.http.get<any>(
//             `${this.baseUrl}/search?keyword=${keyword}&page=${page}&size=${size}`
//         );
//     }
// }

import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class DiseaseService {

  private baseUrl = 'http://localhost:8080/api/diseases';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.baseUrl);
  }

  searchDiseases(keyword='',page=0,size=10){
        return this.http.get<any>(
            `${this.baseUrl}/search?keyword=${keyword}&page=${page}&size=${size}`
        );
    }

}
