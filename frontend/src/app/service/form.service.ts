 import { Injectable } from '@angular/core';
 import {HttpClient} from "@angular/common/http";
 import {FormDataModel} from "../model/formData.model";
 import {Observable} from "rxjs";

 const BASE_URL = "http://localhost:8080/api/forms";

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor(private http: HttpClient) { }

    sendFormData(data: FormDataModel):Observable<any>{
    return this.http.post(BASE_URL, data);
    }


}
