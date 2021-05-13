import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipesService } from 'src/app/services/recipes.service';
import { Ingredient } from 'src/app/shared/ingredient.model';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  isNew: boolean;
  recipeId: number;
  recipe: Recipe = {
    id: '',
    name: '',
    description: '',
    imagePath: '',
    ingredients: []
  }
  form: FormGroup;
  constructor(private route: ActivatedRoute, private recipeService: RecipesService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.isNew = params['id'] === undefined
      if (!this.isNew) {
        this.recipeId = +params['id']
        this.recipeService.getRecipeById(params['id']).subscribe((recipe: Recipe) => {
          this.recipe = recipe
          this.initForm(this.recipe.name, this.recipe.description, this.recipe.imagePath, this.recipe.ingredients)
        })

      } else {
        this.initForm(null, null, null, null)
      }
    })
  }

  initForm(defName: string, defDescription: string, defImagePath: string, defIngredients: Ingredient[]) {
    let formArray = new FormArray([])
    if (defIngredients !== null) {
      for (let i = 0; i < defIngredients.length; i++) {
        this.initIngredients(formArray, defIngredients[i].name, defIngredients[i].amount)
      }
    }
    this.form = new FormGroup({
      'name': new FormControl(defName, Validators.required),
      'description': new FormControl(defDescription, Validators.required),
      'imagePath': new FormControl(defImagePath, Validators.required),
      'ingredients': defIngredients !== null ? formArray : new FormArray([])
    })
  }

  onAddIngredient() {
    (<FormArray>this.form.get('ingredients')).push(
      new FormGroup({
        'name': new FormControl(null, Validators.required),
        'amount': new FormControl(null, [Validators.required, this.amountValidator.bind(this)])
      })
    )
  }

  initIngredients(formArray: FormArray, name: string, amount: number) {
    formArray.push(
      new FormGroup({
        'name': new FormControl(name, Validators.required),
        'amount': new FormControl(amount, [Validators.required, this.amountValidator.bind(this)])
      })
    )
  }


  getIngredientsControl() {
    return (<FormArray>this.form.get('ingredients')).controls
  }

  amountValidator(control: FormControl): { [s: string]: boolean } {
    if (control.value <= 0) {
      return { invalidAmount: true }
    }
  }

  deleteIngredient(index: number) {
    (<FormArray>this.form.get('ingredients')).removeAt(index)
  }

  onSubmit() {
    this.recipe.name = this.form.get('name').value
    this.recipe.description = this.form.get('description').value
    this.recipe.imagePath = this.form.get('imagePath').value
    this.recipe.ingredients = (<FormArray>this.form.get('ingredients')).value
    if (this.isNew) {
      this.recipeService.addRecipeDb(this.recipe)
    } else {
      this.recipeService.updateRecipe(this.recipe)
    }
    this.router.navigate(['recipes'])
  }
}
