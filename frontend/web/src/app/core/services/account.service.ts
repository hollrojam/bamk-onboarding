import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { map, Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { ApiResponse } from "../models/api-response";
import { Account, CreateAccountRequest } from "../models/account";

@Injectable({ providedIn: "root" })
export class AccountService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiBaseUrl;

  createAccount(payload: CreateAccountRequest): Observable<Account> {
    return this.http
      .post<ApiResponse<Account>>(`${this.baseUrl}/api/accounts`, payload)
      .pipe(map((response) => response.data));
  }

  getAccountsByCustomerId(customerId: string): Observable<Account[]> {
    return this.http
      .get<ApiResponse<Account[]>>(`${this.baseUrl}/api/accounts`, { params: { customerId } })
      .pipe(map((response) => response.data));
  }
}
