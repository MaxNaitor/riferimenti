import { Component, Input, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-visualizzazione',
  templateUrl: './visualizzazione.component.html',
  styleUrls: ['./visualizzazione.component.css']
})
export class VisualizzazioneComponent implements OnInit {

  @Input() nome: string;

  constructor(private service: ServiceService) {

  }

  ngOnInit(): void {
    // this.service.onAdd.subscribe((nome: string) => alert(nome + " aggiunto alla lista"))
  }

}
