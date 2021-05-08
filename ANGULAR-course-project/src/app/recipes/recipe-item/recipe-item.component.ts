import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RecipesService } from 'src/app/services/recipes.service';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent implements OnInit {

  @Input() recipeId: number
  @Input() recipe: Recipe;

  select() {
    this.recipesService.recipeSelected.emit(this.recipe)
  }

  constructor(private recipesService: RecipesService) { }

  ngOnInit() {
  }

}
