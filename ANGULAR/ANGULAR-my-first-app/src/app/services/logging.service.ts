import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggingService {

  logToConsole(msg: string) {
    console.log(msg)
  }
  
  constructor() { }
}
