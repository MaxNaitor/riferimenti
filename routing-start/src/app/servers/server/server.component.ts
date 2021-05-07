import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { ServersService } from '../servers.service';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent implements OnInit {
  server: { id: number, name: string, status: string };
  routeSub: Subscription;

  constructor(private serversService: ServersService, private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    let selectedServerId = +this.activatedRoute.snapshot.params['id']

    this.routeSub = this.activatedRoute.params.subscribe((params: Params) => {
      selectedServerId = +params['id']
      this.server = this.serversService.getServer(selectedServerId);
    })

    this.server = this.serversService.getServer(selectedServerId);
  }

  onEdit() {
    // this.router.navigate(['edit'], {relativeTo: this.activatedRoute})
    this.router.navigate(['/servers',this.server.id,'edit'],{queryParamsHandling:'preserve'})
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe()
  }

}
