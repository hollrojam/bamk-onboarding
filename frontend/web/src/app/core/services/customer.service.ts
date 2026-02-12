import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { map, Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { ApiResponse } from "../models/api-response";
import { CreateCustomerRequest, Customer } from "../models/customer";

@Injectable({ providedIn: "root" })
export class CustomerService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiBaseUrl;

  getCustomers(): Observable<Customer[]> {
    return this.http
      .get<ApiResponse<Customer[]>>(`${this.baseUrl}/api/customers`)
      .pipe(map((response) => response.data));
  }

  createCustomer(payload: CreateCustomerRequest): Observable<Customer> {
    return this.http
      .post<ApiResponse<Customer>>(`${this.baseUrl}/api/customers`, payload)
      .pipe(map((response) => response.data));
  }
}
