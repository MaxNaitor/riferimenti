import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {

  allowNewServers = false;
  serverCreationStatus = "No server created!"
  serverName: string;

  onCreateServer() {
    this.serverCreationStatus = "Server created! Name is " + this.serverName
  }

  onUpdateServerName(e: Event) {
    this.serverName = (<HTMLInputElement>e.target).value
  }

  constructor() {
    setTimeout(() => { this.allowNewServers = !this.allowNewServers }, 3000)
  }

  ngOnInit(): void {
  }

}
