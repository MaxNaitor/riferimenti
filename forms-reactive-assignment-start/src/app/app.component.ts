import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  projectForm: FormGroup

  ngOnInit() {
    this.projectForm = new FormGroup({
      'name': new FormControl(null, [Validators.required, /*this.syncValidator.bind(this)*/],this.asyncValidator.bind(this)),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'status': new FormControl('Stable'),
    })
  }

  onSubmit() {
    console.log(this.projectForm.value)
  }

  syncValidator(control: FormControl): { [s: string]: boolean } {
    console.log(control.errors)
    if (control.value === 'Test' || control.value === 'test') {
      return { nameIsInvalid: true }
    }
  }

  asyncValidator(control: FormControl): Promise<any> | Observable<any> {
    const promise = new Promise((resolve, reject) => {
      setTimeout(() => {
        if (control.value === 'Test' || control.value === 'test') {
          resolve({ nameIsInvalid: true })
        } else {
          resolve(null)
        }
      }, 1500)
    })
    return promise;
  }
}
