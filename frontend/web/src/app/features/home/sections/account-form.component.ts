import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from "@angular/core";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { AccountType, CreateAccountRequest } from "../../../core/models/account";

@Component({
  selector: "app-account-form",
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: "./account-form.component.html",
  styleUrl: "./account-form.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AccountFormComponent implements OnChanges {
  @Input() customerId: string | null = null;
  @Output() submitAccount = new EventEmitter<CreateAccountRequest>();
  @Output() loadAccounts = new EventEmitter<string>();

  accountTypes: AccountType[] = ["AHORRO", "CORRIENTE"];

  form = new FormBuilder().nonNullable.group({
    customerId: ["", [Validators.required, Validators.minLength(4)]],
    tipo: ["AHORRO" as AccountType, Validators.required],
    saldoInicial: [0, [Validators.required, Validators.min(0.01)]]
  });

  searchForm = new FormBuilder().nonNullable.group({
    customerId: ["", [Validators.required, Validators.minLength(4)]]
  });

  ngOnChanges(changes: SimpleChanges): void {
    const next = changes["customerId"]?.currentValue as string | null | undefined;
    if (next) {
      this.form.patchValue({ customerId: next });
      this.searchForm.patchValue({ customerId: next });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitAccount.emit(this.form.getRawValue());
    this.form.reset({
      customerId: "",
      tipo: "AHORRO",
      saldoInicial: 0
    });
  }

  onSearch(): void {
    if (this.searchForm.invalid) {
      this.searchForm.markAllAsTouched();
      return;
    }
    this.loadAccounts.emit(this.searchForm.getRawValue().customerId);
  }
}
