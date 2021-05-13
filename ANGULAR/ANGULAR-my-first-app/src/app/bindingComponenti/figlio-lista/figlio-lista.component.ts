import { Component, Input, OnInit } from '@angular/core';
import { Persona } from '../persona.model';

@Component({
  selector: 'app-figlio-lista',
  templateUrl: './figlio-lista.component.html',
  styleUrls: ['./figlio-lista.component.css']
})
export class FiglioListaComponent implements OnInit {

  @Input() dipendente: Persona;
  constructor() { }

  ngOnInit(): void {
  }

}
