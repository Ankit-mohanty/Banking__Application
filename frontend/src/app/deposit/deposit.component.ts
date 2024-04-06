import { CurrencyPipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { HeaderComponent } from '../header/header.component';
import { ModalComponent } from '../modal/modal.component';
import { ToastComponent } from '../toast/toast.component';
import { FormsModule, NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { TransactionService } from '../services/transaction.service';
import { Datatable } from '../model/datatable';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [
    CurrencyPipe,
    FormsModule,
    SidebarComponent,
    HeaderComponent,
    ModalComponent,
    ToastComponent,
  ],
  templateUrl: './deposit.component.html',
  styleUrl: './deposit.component.css',
})
export class DepositComponent implements OnInit {
  accountService = inject(AccountService);
  transactionService = inject(TransactionService);
  modalVisible = false;
  toastHeading = '';
  toastDescription = '';
  toastVisible = false;

  totalPage = Array(1);
  datatable!: Datatable;
  pageSize = 5;

  ngOnInit(): void {
    this.getTransactions(1);
  }

  getTransactions(pageNumber: number) {
    this.transactionService
      .getCreditedAmount(pageNumber, this.pageSize)
      .subscribe({
        next: (res) => {
          this.datatable = res;
          this.totalPage = Array(
            Math.ceil(this.datatable.totalRecord / this.pageSize)
          );
        },
        error: (err) => {
          console.log(err);

          const error = err.error;
          this.generateToast(error['title'], error['detail']);
        },
      });
  }

  onPageChange(pageNumber: number) {
    this.getTransactions(pageNumber);
  }

  onDeposit(form: NgForm) {
    if (form.valid) {
      const balance = form.value.balance;
      this.accountService.depositBalance(balance).subscribe({
        next: (res) => {
          this.generateToast('Success', 'Amount deposited');
        },
        error: (err) => {
          console.log(err);

          const error = err.error;
          this.generateToast(error['title'], error['detail']);
        },
        complete: () => {
          form.reset();
          setTimeout(() => this.getTransactions(1), 2000);
          this.modalVisible = false;
        },
      });
    }
  }

  generateToast(heading: string, description: string) {
    this.toastHeading = heading;
    this.toastDescription = description;
    this.toastVisible = true;

    setTimeout(() => {
      this.toastVisible = false;
    }, 5000);
  }
}
