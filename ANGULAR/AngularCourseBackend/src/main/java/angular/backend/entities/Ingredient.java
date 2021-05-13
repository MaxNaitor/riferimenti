package angular.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "shopping-list")
public class Ingredient {
	
	@Id
	private String id;
	private String name;
	private Integer amount;

	public void add (int amount) {
		this.amount += amount;
	}
}
