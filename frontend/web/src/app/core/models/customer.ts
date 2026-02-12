export type DocumentType = "CC" | "CE" | "PAS";

export interface Customer {
  id: string;
  nombre: string;
  email: string;
  fechaRegistro: string;
  estado: string;
}

export interface CreateCustomerRequest {
  documentType: DocumentType;
  documentNumber: string;
  nombre: string;
  email: string;
}
