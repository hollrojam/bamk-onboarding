import { ChangeDetectionStrategy, Component, Input } from "@angular/core";
import { CommonModule } from '@angular/common';
import { Account } from "../../../core/models/account";

@Component({
  selector: "app-account-list",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./account-list.component.html",
  styleUrl: "./account-list.component.scss",
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AccountListComponent {
  @Input({ required: true }) accounts: Account[] = [];
  @Input() loading = false;

  trackByAccount(_: number, item: Account): string {
    return item.accountNumber;
  }
}
