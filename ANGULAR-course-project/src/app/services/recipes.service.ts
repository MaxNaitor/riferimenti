import { EventEmitter, Injectable, Output } from '@angular/core';
import { Recipe } from '../recipes/recipe.model';
import { Ingredient } from '../shared/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  private recipes: Recipe[] = [
    new Recipe('Pancake', 'Boni', 'https://blog.giallozafferano.it/allacciateilgrembiule/wp-content/uploads/2018/03/pancake-ricetta-5.jpg',[new Ingredient('Farina',1),new Ingredient('Zuccero',2)]),
    new Recipe('Penne al tonno', 'Basta tonno', 'https://www.cucchiaio.it/content/cucchiaio/it/ricette/2016/01/penne-ritorte-al-tonno-finocchietto-e-zafferano/_jcr_content/header-par/image-single.img10.jpg/1504538025966.jpg',[new Ingredient('Pasta',1),new Ingredient('Tonno',2)])
  ];

  @Output() recipeSelected = new EventEmitter<Recipe>();

  getRecipes() {
    return this.recipes.slice();
  }
  
  constructor() { }
}
