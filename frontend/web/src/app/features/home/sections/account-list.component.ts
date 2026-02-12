import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output, computed, signal } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MatSlideToggle, MatSlideToggleChange } from "@angular/material/slide-toggle";
import { Account, AccountStatus } from "../../../core/models/account";

@Component({
  selector: "app-account-list",
  standalone: true,
  imports: [CommonModule, MatSlideToggle],
  templateUrl: "./account-list.component.html",
  styleUrl: "./account-list.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AccountListComponent {
  @Input({ required: true }) accounts: Account[] = [];
  @Input() loading = false;
  @Input() customerName: string | null = null;
  @Input() updatingAccountNumbers: Set<string> = new Set<string>();
  @Output() changeStatus = new EventEmitter<{ accountNumber: string; status: AccountStatus }>();

  private readonly sortKey = signal<"customer" | "tipo">("customer");
  private readonly sortDirection = signal<"asc" | "desc">("asc");
  readonly skeletonRows = [1, 2, 3, 4, 5];

  readonly sortedAccounts = computed(() => {
    const key = this.sortKey();
    const direction = this.sortDirection();
    const nameValue = this.customerName ?? "";
    const items = [...this.accounts];
    const compare = (left: Account, right: Account): number => {
      const leftValue = key === "customer" ? nameValue : this.getAccountTypeLabel(left.tipo);
      const rightValue = key === "customer" ? nameValue : this.getAccountTypeLabel(right.tipo);
      return leftValue.localeCompare(rightValue, "es", { sensitivity: "base" });
    };
    items.sort(compare);
    if (direction === "desc") {
      items.reverse();
    }
    return items;
  });

  trackByAccount(_: number, item: Account): string {
    return item.accountNumber;
  }

  onSort(key: "customer" | "tipo"): void {
    if (this.sortKey() === key) {
      this.sortDirection.set(this.sortDirection() === "asc" ? "desc" : "asc");
      return;
    }
    this.sortKey.set(key);
    this.sortDirection.set("asc");
  }

  sortAria(key: "customer" | "tipo"): "ascending" | "descending" | "none" {
    if (this.sortKey() !== key) {
      return "none";
    }
    return this.sortDirection() === "asc" ? "ascending" : "descending";
  }

  isUpdating(accountNumber: string): boolean {
    return this.updatingAccountNumbers.has(accountNumber);
  }

  statusLabel(status: AccountStatus): string {
    return status === "ACTIVE" ? "Activo" : "Inactivo";
  }

  onToggleChange(event: MatSlideToggleChange, account: Account): void {
    if (this.isUpdating(account.accountNumber)) {
      event.source.checked = account.estado === "ACTIVE";
      return;
    }
    const nextStatus: AccountStatus = event.checked ? "ACTIVE" : "INACTIVE";
    const nextLabel = nextStatus === "ACTIVE" ? "activa" : "inactiva";
    const confirmed = window.confirm(`¿Confirmas cambiar el estado de la cuenta ${account.accountNumber} a ${nextLabel}?`);
    if (!confirmed) {
      event.source.checked = account.estado === "ACTIVE";
      return;
    }
    this.changeStatus.emit({ accountNumber: account.accountNumber, status: nextStatus });
  }

  formatBalance(value: string): string {
    const numeric = Number(value);
    if (Number.isNaN(numeric)) {
      return value;
    }
    return new Intl.NumberFormat("es-CO", { style: "currency", currency: "COP" }).format(numeric);
  }

  getAccountTypeLabel(tipo: string): string {
    if (tipo === "AHORRO") {
      return "Ahorros";
    }
    if (tipo === "CORRIENTE") {
      return "Corriente";
    }
    return tipo;
  }
}
