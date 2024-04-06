import { CurrencyPipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { HeaderComponent } from '../header/header.component';
import { DashboardService } from '../services/dashboard.service';
import { Dashboard } from '../model/dashboard';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CurrencyPipe, SidebarComponent, HeaderComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  dashboardService = inject(DashboardService);
  dashboard!: Dashboard;

  ngOnInit(): void {
    this.dashboardService.getDashboardDetails().subscribe({
      next: res => {
        this.dashboard = res;
      }
    })  
  }
}
