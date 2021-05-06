import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CounterService } from '../services/counter.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-inactive-users',
  templateUrl: './inactive-users.component.html',
  styleUrls: ['./inactive-users.component.css']
})
export class InactiveUsersComponent {
  users: string[];

  constructor(private usersService: UsersService, private counterService: CounterService) { }

  ngOnInit() {
    this.users = this.usersService.inactiveUsers
  }

  onSetToActive(id: number) {
    this.usersService.toActive(id)
    this.counterService.increment()
  }
}
