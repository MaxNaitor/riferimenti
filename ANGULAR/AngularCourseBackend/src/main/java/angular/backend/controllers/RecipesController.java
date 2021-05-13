package angular.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import angular.backend.entities.Recipe;
import angular.backend.services.RecipeService;

@RestController
@RequestMapping(path = "/api/recipes")
@CrossOrigin
public class RecipesController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public List<Recipe> getRecipes() {
		return recipeService.getAllRecipes();
	}

	@PostMapping
	public List<Recipe> saveRecipe(@RequestBody Recipe recipe) {
		return recipeService.insertRecipe(recipe);
	}

	@GetMapping("/{id}")
	public Recipe getRecipe(@PathVariable("id") String id) {
		return recipeService.getRecipe(id);
	}

	@DeleteMapping("/{id}")
	public List<Recipe> deleteRecipe(@PathVariable("id") String id) {
		return recipeService.deleteRecipe(id);
	}

	@PostMapping("/update")
	public List<Recipe> updateRecipe(@RequestBody Recipe recipe) {
		return recipeService.updateRecipe(recipe);
	}
}
