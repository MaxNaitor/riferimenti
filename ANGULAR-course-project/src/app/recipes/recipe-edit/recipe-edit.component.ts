import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RecipesService } from 'src/app/services/recipes.service';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  isNew: boolean;
  recipe: Recipe;
  constructor(private route: ActivatedRoute,private recipeService: RecipesService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.isNew = params['id'] === undefined
      if (!this.isNew) {
        this.recipe = this.recipeService.getRecipe(+params['id'])
      }
    })
  }

}
