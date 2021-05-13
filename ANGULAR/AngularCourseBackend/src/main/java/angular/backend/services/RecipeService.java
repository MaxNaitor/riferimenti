package angular.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import angular.backend.entities.Recipe;
import angular.backend.repositories.RecipesRepo;

@Service
public class RecipeService {

	@Autowired
	private RecipesRepo recipeRepo;

	public List<Recipe> getAllRecipes() {
		return recipeRepo.findAll();
	}

	public List<Recipe> insertRecipe(Recipe recipe) {
		recipeRepo.insert(recipe);
		return recipeRepo.findAll();
	}

	public Recipe getRecipe(String id) {
		return recipeRepo.findById(id).get();
	}

	public List<Recipe> deleteRecipe(String id) {
		recipeRepo.deleteById(id);
		return recipeRepo.findAll();
	}

	public List<Recipe> updateRecipe(Recipe recipe) {
		recipeRepo.save(recipe);
		return recipeRepo.findAll();
	}

}
