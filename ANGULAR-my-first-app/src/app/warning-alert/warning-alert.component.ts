import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-warning-alert',
  templateUrl: './warning-alert.component.html',
  styleUrls: ['./warning-alert.component.css']
})
export class WarningAlertComponent implements OnInit {

  messaggio = "";

  constructor() { }

  ngOnInit(): void {
  }

  setMessage(messaggio){
    this.messaggio = messaggio
  }

}
