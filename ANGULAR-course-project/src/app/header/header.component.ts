import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() toggleComponents = new EventEmitter<{componentName: string}>();

  componentName: string;

  handleClick(compName: string) {
    this.componentName = compName;
    this.toggle()
  }

  toggle() {
    this.toggleComponents.emit({componentName: this.componentName})
  }

  constructor() { }

  ngOnInit(): void {
  }

}