import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Organization} from "../model/organization";

@Injectable({
  providedIn: 'root'
})
export class OrganizationCreationService {
  private apiUrl = '/api/organizations';

  constructor(private http: HttpClient) {}

  createOrganization(OrganizationData: Organization): Observable<any> {
    const registrationEndpoint = this.apiUrl;

    return this.http.post(registrationEndpoint, OrganizationData);
  }
}
