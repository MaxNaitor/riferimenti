import { Component, OnInit } from '@angular/core';
import { LoggingService } from '../services/logging.service';

@Component({
  selector: 'app-esercizio-direttive',
  templateUrl: './esercizio-direttive.component.html',
  styleUrls: ['./esercizio-direttive.component.css']
})
export class EsercizioDirettiveComponent implements OnInit {

  mostra = false;
  click = 0;
  clickArray = [0]

  toggle() {
    this.mostra = !this.mostra
    this.click++
    this.clickArray.push(this.click);
    this.logger.logToConsole('service')
  }

  blueBackground() {
    if (this.click >= 5) {
      return 'blue'
    }
  }

  constructor(private logger: LoggingService) { }

  ngOnInit(): void {
  }

}
