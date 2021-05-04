import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {

  allowNewServers = false;
  serverCreationStatus: string;
  serverName: string;
  serverCreated = false;
  servers:string[] = []

  onCreateServer() {
    this.serverCreationStatus = "Server created! Name is " + this.serverName
    this.serverCreated = true;
    this.servers.push(this.serverName)
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
