import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private subscription: Subscription;

  constructor() { }

  ngOnInit() {
    const observable = Observable.create(observer => {
      let count = 0
      setInterval(() => {
        observer.next(count)
        count++
        if (count === 2) {
          observer.complete()
        }
        if (count > 3) {
          observer.next(count)
          observer.error(new Error('Count greater than 3!'));
        }
      }, 1000)
    })

    this.subscription = observable.pipe(map(data => {
      return 'Round: ' + (+data + 1);
    })).subscribe(data => {
      console.log(data)
    }, error => {
      console.log(error)
    })

    // this.subscription = observable.subscribe(data => {
    //   console.log(data)
    // }, error => {
    //   console.log(error)
    // })
  }

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }

}
