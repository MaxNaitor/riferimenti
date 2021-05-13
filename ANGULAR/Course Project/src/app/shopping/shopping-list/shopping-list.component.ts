import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ShoppingService } from 'src/app/services/shopping.service';
import { Ingredient } from 'src/app/shared/ingredient.model';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {

  ingredients: Ingredient[];
  private subscription: Subscription

  constructor(private shoppingService: ShoppingService) { }

  ngOnInit(): void {
    this.shoppingService.getIngredients().subscribe((ingredients: Ingredient[]) => {
      this.ingredients = ingredients
    })
    this.subscription = this.shoppingService.updateList.subscribe((newList: Ingredient[]) => this.ingredients = newList)
  }

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }

}
