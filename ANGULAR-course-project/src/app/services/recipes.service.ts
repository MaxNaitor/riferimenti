import { EventEmitter, Injectable, Output } from '@angular/core';
import { Recipe } from '../recipes/recipe.model';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  private recipes: Recipe[] = [
    new Recipe('Test', 'Test', 'https://blog.giallozafferano.it/allacciateilgrembiule/wp-content/uploads/2018/03/pancake-ricetta-5.jpg'),
    new Recipe('Test2', 'Test2', 'https://www.cucchiaio.it/content/cucchiaio/it/ricette/2016/01/penne-ritorte-al-tonno-finocchietto-e-zafferano/_jcr_content/header-par/image-single.img10.jpg/1504538025966.jpg')
  ];

  @Output() recipeSelected = new EventEmitter<Recipe>();

  getRecipes() {
    return this.recipes.slice();
  }
  
  constructor() { }
}
