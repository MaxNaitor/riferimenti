import { Component, OnInit } from '@angular/core';
import { Persona } from '../persona.model';

@Component({
  selector: 'app-padre',
  templateUrl: './padre.component.html',
  styleUrls: ['./padre.component.css']
})
export class PadreComponent implements OnInit {

  dipendenti:Persona[] = [new Persona('Tiziano','Massa')];

  onAssunzione(dipendente:Persona) {
    this.dipendenti.push(dipendente);
  }

  constructor() { }

  ngOnInit(): void {
  }

}
