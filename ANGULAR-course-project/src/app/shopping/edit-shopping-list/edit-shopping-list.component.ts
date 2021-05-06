import { Component, OnInit} from '@angular/core';
import { ShoppingService } from 'src/app/services/shopping.service';
import { Ingredient } from 'src/app/shared/ingredient.model';

@Component({
  selector: 'app-edit-shopping-list',
  templateUrl: './edit-shopping-list.component.html',
  styleUrls: ['./edit-shopping-list.component.css']
})
export class EditShoppingListComponent implements OnInit {

  //@ViewChild recupera l'elemento contrassegnato da #nameInput che sarà di tipo ElementRef
  // il valore dell'input sarà accessibile con .nativeElement.value
  //@ViewChild('nameInput',{static: false}) nameInputRef: ElementRef;

  constructor(private shoppingService: ShoppingService) { }

  ngOnInit(): void {
  }

  aggiungiIngrediente(name: string, amount: string) {
    this.shoppingService.addToList(new Ingredient(name,parseInt(amount,10)))
  }

  



}
