import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Ingredient } from '../shared/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  baseURL = 'http://localhost:8080/api/ingredients/'
  updateList = new Subject<Ingredient[]>()

  constructor(private http: HttpClient) { }

  addToList(ingredient: Ingredient) {
    this.http.post(this.baseURL, [ingredient]).subscribe((newList: Ingredient[]) => {
      this.updateList.next(newList)
    })
  }

  addRecipeIngredientsToList(ingredients: Ingredient[]) {
    this.http.post(this.baseURL, ingredients).subscribe((newList: Ingredient[]) => {
      this.updateList.next(newList)
    })
  }

  delete(id: string) {
    this.http.delete(this.baseURL + id).subscribe((newList: Ingredient[]) => {
      this.updateList.next(newList)
    })
  }

  updateIngredient(ingredient: Ingredient) {
    this.http.post(this.baseURL + 'update', ingredient).subscribe((newList: Ingredient[]) => {
      this.updateList.next(newList)
    })
  }

  getIngredients() {
    return this.http.get(this.baseURL + 'shopping-list')
  }

  getIngredient(id: string) {
    return this.http.get(this.baseURL + id)
  }


}
