import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Dashboard } from '../model/dashboard';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  BASE_URL = environment.base_url + "/dashboards";
  http = inject(HttpClient);

  constructor() { }

  getDashboardDetails() {
    return this.http.get<Dashboard>(this.BASE_URL);
  }
}
