import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Customer } from "../../../core/models/customer";

@Component({
  selector: "app-customer-list",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./customer-list.component.html",
  styleUrl: "./customer-list.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CustomerListComponent {
  @Input({ required: true }) customers: Customer[] = [];
  @Input() loading = false;
  @Output() selectCustomer = new EventEmitter<string>();

  trackById(_: number, item: Customer): string {
    return item.id;
  }

  onSelect(customerId: string): void {
    this.selectCustomer.emit(customerId);
  }
}
