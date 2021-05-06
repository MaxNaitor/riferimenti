import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CounterService } from '../services/counter.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-active-users',
  templateUrl: './active-users.component.html',
  styleUrls: ['./active-users.component.css']
})
export class ActiveUsersComponent {
  users: string[];

  constructor(private usersService: UsersService, private counterService: CounterService) {
    this.users = usersService.activeUsers
  }

  setToInactive(id: number) {
    this.usersService.toInactive(id)
    this.counterService.increment();
  }
}
