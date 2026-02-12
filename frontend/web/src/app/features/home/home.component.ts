import { ChangeDetectionStrategy, Component, DestroyRef, inject, signal } from "@angular/core";
import { CommonModule } from "@angular/common";
import { takeUntilDestroyed } from "@angular/core/rxjs-interop";
import { CustomerService } from "../../core/services/customer.service";
import { AccountService } from "../../core/services/account.service";
import { CustomerFormComponent } from "./sections/customer-form.component";
import { CustomerListComponent } from "./sections/customer-list.component";
import { AccountFormComponent } from "./sections/account-form.component";
import { AccountListComponent } from "./sections/account-list.component";
import { CreateCustomerRequest, Customer } from "../../core/models/customer";
import { Account, CreateAccountRequest } from "../../core/models/account";

@Component({
  selector: "app-home",
  standalone: true,
  imports: [CommonModule, CustomerFormComponent, CustomerListComponent, AccountFormComponent, AccountListComponent],
  templateUrl: "./home.component.html",
  styleUrl: "./home.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HomeComponent {
  private readonly customerService = inject(CustomerService);
  private readonly accountService = inject(AccountService);
  private readonly destroyRef = inject(DestroyRef);

  customers = signal<Customer[]>([]);
  accounts = signal<Account[]>([]);
  customersLoading = signal(false);
  accountsLoading = signal(false);
  lastCustomerId = signal<string | null>(null);
  selectedCustomerId = signal<string | null>(null);
  message = signal<string | null>(null);

  constructor() {
    this.loadCustomers();
  }

  onCreateCustomer(payload: CreateCustomerRequest): void {
    this.message.set(null);
    this.customerService
      .createCustomer(payload)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (customer) => {
          this.customers.update((items) => [customer, ...items]);
          this.message.set("Cliente creado correctamente");
        },
        error: () => {
          this.message.set("No se pudo crear el cliente");
        }
      });
  }

  onRefreshCustomers(): void {
    this.loadCustomers();
  }

  onSelectCustomer(customerId: string): void {
    this.selectedCustomerId.set(customerId);
    this.lastCustomerId.set(customerId);
    this.onLoadAccounts(customerId);
  }

  onCreateAccount(payload: CreateAccountRequest): void {
    this.message.set(null);
    this.accountService
      .createAccount(payload)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (account) => {
          this.accounts.update((items) => [account, ...items]);
          this.message.set("Cuenta creada correctamente");
        },
        error: () => {
          this.message.set("No se pudo crear la cuenta");
        }
      });
  }

  onLoadAccounts(customerId: string): void {
    if (!customerId) {
      return;
    }
    this.accountsLoading.set(true);
    this.lastCustomerId.set(customerId);
    this.accountService
      .getAccountsByCustomerId(customerId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (accounts) => {
          this.accounts.set(accounts);
          this.accountsLoading.set(false);
        },
        error: () => {
          this.accounts.set([]);
          this.accountsLoading.set(false);
        }
      });
  }

  private loadCustomers(): void {
    this.customersLoading.set(true);
    this.customerService
      .getCustomers()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (customers) => {
          this.customers.set(customers);
          this.customersLoading.set(false);
        },
        error: () => {
          this.customers.set([]);
          this.customersLoading.set(false);
        }
      });
  }
}
