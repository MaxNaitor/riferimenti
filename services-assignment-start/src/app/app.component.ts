import { Component } from '@angular/core';
import { CounterService } from './services/counter.service';
import { UsersService } from './services/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  count: number = 0;

  constructor(private counterService: CounterService) {
    this.counterService.countUpdate.subscribe((newCount: number) => this.count = newCount);
  }

}
