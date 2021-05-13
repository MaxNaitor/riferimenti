package angular.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import angular.backend.entities.Recipe;

public interface RecipesRepo extends MongoRepository<Recipe, String>{

}
