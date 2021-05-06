import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Ingredient } from 'src/app/shared/ingredient.model';

@Component({
  selector: 'app-edit-shopping-list',
  templateUrl: './edit-shopping-list.component.html',
  styleUrls: ['./edit-shopping-list.component.css']
})
export class EditShoppingListComponent implements OnInit {

  @Output() onAddButton = new EventEmitter<Ingredient>();
  //@ViewChild recupera l'elemento contrassegnato da #nameInput che sarà di tipo ElementRef
  // il valore dell'input sarà accessibile con .nativeElement.value
  //@ViewChild('nameInput',{static: false}) nameInputRef: ElementRef;

  aggiungiIngrediente(name: string, amount: string) {
    this.onAddButton.emit(new Ingredient(name, parseInt(amount, 10)))
  }

  constructor() { }

  ngOnInit(): void {
  }

}
