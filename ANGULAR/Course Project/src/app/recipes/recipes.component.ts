import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RecipesService } from '../services/recipes.service';
import { Recipe } from './recipe.model';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  selectedRecipe: Recipe;
  private subscription: Subscription

  constructor(private recipesService: RecipesService) { }

  ngOnInit(): void {
    this.subscription = this.recipesService.recipeSelected.subscribe((recipe: Recipe) => this.selectedRecipe = recipe)
  }

  ngOnDestory() {
    this.subscription.unsubscribe()
  }

}
