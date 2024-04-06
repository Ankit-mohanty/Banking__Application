  import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Account } from '../model/account';
import { environment } from '../../environments/environment.development';

export const BASE_URL = environment.base_url + '/accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  http = inject(HttpClient);
  noauth = { headers: { "noauth": "noauth" } };

  constructor() { }

  createAccount(account: any) {
    return this.http.post(BASE_URL + "/register", account, this.noauth);
  }

  loginAccount(account: any) {
    return this.http.post(BASE_URL + "/login", account, this.noauth);
  }

  depositBalance(balance: any) {

    return this.http.patch(BASE_URL + "/deposit/" + balance, {});
  }

  withdrawalBalance(balance: any) {
    return this.http.patch(BASE_URL + "/withdrawal/" + balance, {});
  }

  transferBalance(balance: any, reciever: any) {
    return this.http.patch(BASE_URL + "/transfer/" + reciever + "/balance/" + balance, {});
  }

  getCurrentAccount() {
    return this.http.get<Account>(BASE_URL + "/current");
  }

  updateAccount(account: Account) {
    const accountNumber = account.accountNumber;
    return this.http.put<Account>(`${BASE_URL}/${accountNumber}`, account);
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post<Account>(BASE_URL + "/image", formData);
  }
}
