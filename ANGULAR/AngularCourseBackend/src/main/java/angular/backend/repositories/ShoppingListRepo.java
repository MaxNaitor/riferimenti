package angular.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import angular.backend.entities.Ingredient;

public interface ShoppingListRepo extends MongoRepository<Ingredient, String>{

}
