import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CounterService {

  private count: number = 0;
  @Output() countUpdate = new EventEmitter<number>()

  increment() {
    this.count++;
    this.countUpdate.emit(this.count)
  }

  getCount() {
    return this.count;
  }

  constructor() { }
}
