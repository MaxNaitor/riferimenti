import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-inserimento',
  templateUrl: './inserimento.component.html',
  styleUrls: ['./inserimento.component.css']
})
export class InserimentoComponent implements OnInit {

  nome: string;
  nomi: string[];

  handleClick() {
    this.service.push(this.nome)
    this.nome = undefined;
  }

  constructor(private service: ServiceService) {
    this.nomi = service.nomi
   }

  ngOnInit(): void {
  }

}
