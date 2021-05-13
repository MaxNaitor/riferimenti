import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipesService } from 'src/app/services/recipes.service';
import { ShoppingService } from 'src/app/services/shopping.service';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  recipe: Recipe;

  constructor(private shoppingService: ShoppingService, private recipesService: RecipesService, private route: ActivatedRoute, private router: Router) { }

  toShoppingList() {
    this.shoppingService.addRecipeIngredientsToList(this.recipe.ingredients)
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.recipesService.getRecipeById(params['id']).subscribe((resultData: Recipe) => {
        this.recipe = resultData
      })
    })
  }

  onEdit() {
    this.router.navigate(['edit'], { relativeTo: this.route })
  }

  onDelete() {
    if (confirm('Delete the recipe?')) {
      this.recipesService.deleteRecipe(this.recipe.id)
      this.router.navigate(['recipes'])
    }
  }

}
