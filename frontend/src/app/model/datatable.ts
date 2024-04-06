export interface Datatable {
    totalRecord: number
    pageNumber: number
    pageSize: number
    transactions: Transaction[]
}

export interface Transaction {
    timestamp: string
    mode: string
    balance: number
}
