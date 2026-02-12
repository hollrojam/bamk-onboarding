export type AccountType = "AHORRO" | "CORRIENTE";
export type AccountStatus = "ACTIVE" | "INACTIVE";

export interface Account {
  accountNumber: string;
  tipo: string;
  saldoActual: string;
  fechaApertura: string;
  estado: AccountStatus;
}

export interface CreateAccountRequest {
  customerId: string;
  tipo: AccountType;
  saldoInicial: number;
}
