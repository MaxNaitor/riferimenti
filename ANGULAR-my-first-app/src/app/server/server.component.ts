import { Component } from "@angular/core";

@Component({
    selector: 'app-server',
    templateUrl: './server.component.html',
    styles: [`
        .online {
            color: white
        }
        .offline {
            color: blue
        }
        `]
})

export class ServerComponent {

    ID: number = 1;
    status: string;

    constructor() {
        this.status = Math.random() > 0.5 ? 'online' : 'offline'
    }

    getCSSColor() {
        return this.status === 'online' ? 'green' : 'red'
    }
}