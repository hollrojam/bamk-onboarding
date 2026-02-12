export type AccountType = "AHORRO" | "CORRIENTE";

export interface Account {
  accountNumber: string;
  tipo: string;
  saldoActual: string;
  fechaApertura: string;
}

export interface CreateAccountRequest {
  customerId: string;
  tipo: AccountType;
  saldoInicial: number;
}
