import { ChangeDetectionStrategy, Component, EventEmitter, Output } from "@angular/core";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { CreateCustomerRequest, DocumentType } from "../../../core/models/customer";

@Component({
  selector: "app-customer-form",
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: "./customer-form.component.html",
  styleUrl: "./customer-form.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CustomerFormComponent {
  @Output() submitCustomer = new EventEmitter<CreateCustomerRequest>();

  documentTypes: DocumentType[] = ["CC", "CE", "PAS"];

  form = new FormBuilder().nonNullable.group({
    documentType: ["CC" as DocumentType, Validators.required],
    documentNumber: ["", [Validators.required, Validators.minLength(5)]],
    nombre: ["", [Validators.required, Validators.minLength(3)]],
    email: ["", [Validators.required, Validators.email]]
  });

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitCustomer.emit(this.form.getRawValue());
    this.form.reset({
      documentType: "CC",
      documentNumber: "",
      nombre: "",
      email: ""
    });
  }
}
