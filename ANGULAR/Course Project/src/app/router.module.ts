import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RecipeDetailComponent } from "./recipes/recipe-detail/recipe-detail.component";
import { RecipeEditComponent } from "./recipes/recipe-edit/recipe-edit.component";
import { RecipesComponent } from "./recipes/recipes.component";
import { EditShoppingListComponent } from "./shopping/edit-shopping-list/edit-shopping-list.component";
import { ShoppingListComponent } from "./shopping/shopping-list/shopping-list.component";
import { StartingComponent } from "./starting/starting.component";

const routes: Routes = [
    { path: '', redirectTo: '/recipes', pathMatch: 'full'},
    { path: 'recipes', component: RecipesComponent, children: [
        {path: '', component: StartingComponent, pathMatch: 'full'},
        {path: 'new', component: RecipeEditComponent},
        {path: ':id', component: RecipeDetailComponent},        
        {path: ':id/edit', component: RecipeEditComponent},
    ]},
    { path: 'shopping', component: ShoppingListComponent, children: [
        {path: '', component: EditShoppingListComponent, pathMatch: 'full'},
        {path: ':id/edit',component: EditShoppingListComponent}
    ] },
    { path: '**', redirectTo: '' }
]

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})

export class AppRouter {

}