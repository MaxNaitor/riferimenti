import { EventEmitter, Injectable, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Ingredient } from '../shared/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private ingredients: Ingredient[] = [
    new Ingredient('Apples', 5),
    new Ingredient('Tomatoes', 15)
  ]

  // @Output() updateList = new EventEmitter<Ingredient[]>();
  updateList = new Subject<Ingredient[]>()

  addToList(ingredient: Ingredient) {
    this.ingredients.push(ingredient)
    this.updateList.next(this.ingredients.slice());
  }

  addRecipeIngredientsToList(ingredients: Ingredient[]) {
    this.ingredients.push(...ingredients) //lo spread operator è una shortcut che simula un ciclo per inserire uno ad uno gli elementi dell'array
    this.updateList.next(this.ingredients.slice());
  }

  delete(id: number) {
    this.ingredients.splice(id, 1)
    this.updateList.next(this.ingredients.slice())
  }

  getIngredients() {
    return this.ingredients.slice()
  }

  getIngredient(id: number) {
    return this.ingredients[id]
  }

  constructor() { }
}
