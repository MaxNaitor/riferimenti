package angular.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "recipes")
public class Recipe {
	
	@Id
	private String id;
	private String name;
	private String description;
	private String imagePath;
	private Ingredient[] ingredients;

}
