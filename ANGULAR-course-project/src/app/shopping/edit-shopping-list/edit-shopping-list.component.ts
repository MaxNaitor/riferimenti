import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ShoppingService } from 'src/app/services/shopping.service';
import { Ingredient } from 'src/app/shared/ingredient.model';

@Component({
  selector: 'app-edit-shopping-list',
  templateUrl: './edit-shopping-list.component.html',
  styleUrls: ['./edit-shopping-list.component.css']
})
export class EditShoppingListComponent implements OnInit {


  @ViewChild('form', { static: true }) form: NgForm

  selectedIngredient: Ingredient = {
    id: '',
    name: '',
    amount: undefined
  }
  formIsValid = false;
  editMode = false;
  buttonValue: string;

  constructor(private shoppingService: ShoppingService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.form.valueChanges.subscribe(values => {
      if (this.form.valid && values.amount > 0) {
        this.formIsValid = true
      } else {
        this.formIsValid = false
      }
    })

    this.activatedRoute.params.subscribe((params: Params) => {
      if (params['id'] !== undefined) {
        this.editMode = true;
        this.buttonValue = 'Update'
        this.shoppingService.getIngredient(params['id']).subscribe((ingredient: Ingredient) => {
          this.selectedIngredient = ingredient
        })
      } else {
        this.editMode = false;
        this.buttonValue = 'Add'
      }
    })
  }

  onSubmit() {
    if (this.editMode) {
      this.modificaIngrediente()
    } else {
      this.aggiungiIngrediente()
    }
  }

  aggiungiIngrediente() {
    this.shoppingService.addToList(new Ingredient(null, this.form.value.name, +this.form.value.amount))
  }

  modificaIngrediente() {
    this.selectedIngredient.name = this.form.value.name
    this.selectedIngredient.amount = this.form.value.amount
    this.shoppingService.updateIngredient(this.selectedIngredient)
    this.router.navigate(['shopping'])
  }

  clear() {
    this.form.reset()
    this.router.navigate(['shopping'])
  }

  delete() {
    this.shoppingService.delete(this.selectedIngredient.id)
    this.router.navigate(['shopping'])
  }





}
