import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  @ViewChild('form') inputForm: NgForm
  user = {
    email: '',
    subscription: '',
    password: '',
  }

  onSubmit() {
    this.user.email = this.inputForm.value.email
    this.user.subscription = this.inputForm.value.subscription
    this.user.password = this.inputForm.value.password
    console.log(this.user)
    this.inputForm.form.reset()
  }
}
