import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-success-alert',
  templateUrl: './success-alert.component.html',
  styleUrls: ['./success-alert.component.css']
})
export class SuccessAlertComponent implements OnInit {

  messaggio = "Successo!";
  conteggio = 0;

  constructor() { }

  ngOnInit(): void {
  }

  aumenta() {
    this.conteggio++
  }

}
