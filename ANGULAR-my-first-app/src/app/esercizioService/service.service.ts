import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  nomi: string[] = []
  @Output() onAdd = new EventEmitter<string>();

  push(nome: string) {
    this.nomi.push(nome)
    this.onAdd.emit(nome)
  }

  constructor() { }
}
