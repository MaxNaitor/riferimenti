import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: { id: number, name: string };

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    //uso snapshot.params per inizializzare LA PRIMA VOLTA il mio oggetto
    this.user = {
      id: this.route.snapshot.params['id'],
      name: this.route.snapshot.params['name']
    }

    //ad ogni successiva chiamata,soprattutto se effettuttuata all'interno dello stesso componente,uso l' Observable params,a cui mi iscrivo
    //per ricevere gli aggiornamenti
    this.route.params.subscribe((params: Params) => {
      this.user.id = params['id']
      this.user.name = params['name']
    })
  }

}
