import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes:Recipe[] = [
    new Recipe('Test','Test','https://blog.giallozafferano.it/allacciateilgrembiule/wp-content/uploads/2018/03/pancake-ricetta-5.jpg'),
    new Recipe('Test2','Test2','https://blog.giallozafferano.it/allacciateilgrembiule/wp-content/uploads/2018/03/pancake-ricetta-5.jpg')
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
