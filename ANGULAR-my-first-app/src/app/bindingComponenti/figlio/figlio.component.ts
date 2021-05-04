import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Persona } from '../persona.model';

@Component({
  selector: 'app-figlio',
  templateUrl: './figlio.component.html',
  styleUrls: ['./figlio.component.css']
})
export class FiglioComponent implements OnInit {

  @Output('assunzione') assumiDipendente = new EventEmitter<Persona>();
  nome: string;
  cognome: string;

  assumi() {
    this.assumiDipendente.emit(new Persona(this.nome, this.cognome))
    this.nome = ''
    this.cognome = ''
  }

  constructor() { }

  ngOnInit(): void {
  }

}
