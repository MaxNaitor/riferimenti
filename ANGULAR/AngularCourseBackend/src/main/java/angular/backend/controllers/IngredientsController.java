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

import angular.backend.entities.Ingredient;
import angular.backend.services.IngredientsService;

@CrossOrigin
@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsService ingredientsService;

	@GetMapping("/shopping-list")
	public List<Ingredient> getShoppingList() {
		return ingredientsService.getShoppingList();
	}

	@GetMapping("/{id}")
	public Ingredient getIngredient(@PathVariable("id") String id) {
		return ingredientsService.getIngredient(id);
	}

	@DeleteMapping("/{id}")
	public List<Ingredient> deleteIngredient(@PathVariable("id") String id) {
		return ingredientsService.deleteIngredient(id);
	}

	@PostMapping
	public List<Ingredient> addToShoppingList(@RequestBody Ingredient[] ingredients) {
		return ingredientsService.addToShoppingList(ingredients);
	}
	
	@PostMapping("/update")
	public List<Ingredient> updateIngredient (@RequestBody Ingredient ingredient) {
		return ingredientsService.updateIngredient(ingredient);
	}
}
