package angular.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import angular.backend.entities.Ingredient;
import angular.backend.repositories.ShoppingListRepo;

@Service
public class IngredientsService {

	@Autowired
	private ShoppingListRepo ingredientsRepo;

	public List<Ingredient> getShoppingList() {
		return ingredientsRepo.findAll();
	}

	public List<Ingredient> addToShoppingList(Ingredient[] ingredients) {
		List<Ingredient> shoppingList = ingredientsRepo.findAll();
		for (Ingredient i : ingredients) {
			boolean isPresent = false;
			for (Ingredient shopIng : shoppingList) {
				if (i.getName().equalsIgnoreCase(shopIng.getName())) {
					shopIng.add(i.getAmount());
					ingredientsRepo.save(shopIng);
					isPresent = true;
					break;
				}
			}
			if (!isPresent) {
				ingredientsRepo.save(i);
			}
		}
		return ingredientsRepo.findAll();
	}

	public Ingredient getIngredient(String id) {
		return ingredientsRepo.findById(id).get();
	}

	public List<Ingredient> deleteIngredient(String id) {
		ingredientsRepo.deleteById(id);
		return ingredientsRepo.findAll();
	}

	public List<Ingredient> updateIngredient(Ingredient ingredient) {
		ingredientsRepo.save(ingredient);
		return ingredientsRepo.findAll();
	}
}
