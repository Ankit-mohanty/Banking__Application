import { Transaction } from "./datatable"

export interface Dashboard {
    numberOfDeposit: number
    numberOfWithdrawal: number
    numberOfTransfer: number
    transactions: Transaction[]
}
