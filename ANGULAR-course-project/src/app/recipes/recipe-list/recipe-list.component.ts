import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipesService } from 'src/app/services/recipes.service';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[]
  selectedRecipe: Recipe;

  constructor(private recipesService: RecipesService, private router: Router, private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.recipes = this.recipesService.getRecipes()
  }

  onNewRecipe() {
    this.router.navigate(['new'],{relativeTo: this.activeRoute})
  }

}
