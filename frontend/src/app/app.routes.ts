import { Routes } from '@angular/router';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepositComponent } from './deposit/deposit.component';
import { WithdrawalComponent } from './withdrawal/withdrawal.component';
import { TransferComponent } from './transfer/transfer.component';
import { ProfileComponent } from './profile/profile.component';

export const routes: Routes = [
    { path: "signin", component: SigninComponent },
    { path: "signup", component: SignupComponent },
    { path: "dashboard", component: DashboardComponent },
    { path: "deposit", component: DepositComponent },
    { path: "withdrawal", component: WithdrawalComponent },
    { path: "transfer", component: TransferComponent },
    { path: "profile", component: ProfileComponent },
    { path: "", pathMatch: "full", redirectTo: "signin" },
    { path: "**", redirectTo: "signin" },
];
