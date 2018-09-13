package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category extends BaseEntity {
	private String categoryName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category{" +
				"categoryName='" + categoryName + '\'' +
				", recipes=" + recipes +
				"} " + super.toString();
	}
}
