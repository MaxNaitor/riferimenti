import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Recipe } from '../recipes/recipe.model';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  private baseURL = 'http://localhost:8080/api/recipes/'

  recipeSelected = new Subject<Recipe>()
  updateRecipeList = new Subject<Recipe[]>()

  constructor(private http: HttpClient) { }

  getAllRecipes() {
    return this.http.get(this.baseURL)
  }

  getRecipeById(id: string) {
    return this.http.get(this.baseURL + id)
  }

  addRecipeDb(recipe: Recipe) {
    this.http.post(this.baseURL, recipe).subscribe((resultData: Recipe[]) => {
      this.updateRecipeList.next(resultData)
    })
  }

  updateRecipe(recipe: Recipe) {
    this.http.post(this.baseURL + 'update', recipe).subscribe((newList: Recipe[]) => {
      this.updateRecipeList.next(newList)
    })

  }

  deleteRecipe(id: string) {
    this.http.delete(this.baseURL + id).subscribe((newList: Recipe[]) => {
      this.updateRecipeList.next(newList)
    })

  }

}
