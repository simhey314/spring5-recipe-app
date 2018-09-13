package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category extends BaseEntity {
	private String name;
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

	public Category() {
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(final Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category{" +
				"name='" + name + '\'' +
				", recipes=" + recipes +
				"} " + super.toString();
	}
}
