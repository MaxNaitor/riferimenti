import { EventEmitter, Injectable, Output } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private ingredients:Ingredient [] = [
    new Ingredient('Apples',5),
    new Ingredient('Tomatoes',15)
  ]

  @Output() updateList = new EventEmitter<Ingredient[]>();

  addToList(ingredient: Ingredient) {
    this.ingredients.push(ingredient)
    this.updateList.emit(this.ingredients);
  }

  getIngredients() {
    return this.ingredients.slice()
  }

  constructor() { }
}
