package guru.springframework.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe extends BaseEntity {

	private String description;
	private Integer prepDuration;
	private Integer cookDuration;
	private Integer serving;
	private String sourceUrl;
	private String sourceName;
	@Lob
	private String directions;
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	@Lob
	private Byte[] image;
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	@ManyToMany
	@JoinTable(name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	public Recipe() {
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(final Set<Category> categories) {
		this.categories = categories;
	}

	public Recipe addCategory(Category category) {
		if (category != null) {
			categories.add(category);
		}

		return this;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(final Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(final Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@NonNull
	public Recipe addIngredient(@Nullable final Ingredient ingredient) {
		if (ingredient != null) {
			ingredients.add(ingredient);
			ingredient.setRecipe(this);
		}
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getPrepDuration() {
		return prepDuration;
	}

	public void setPrepDuration(final Integer prepDuration) {
		this.prepDuration = prepDuration;
	}

	public Integer getCookDuration() {
		return cookDuration;
	}

	public void setCookDuration(final Integer cockDuration) {
		cookDuration = cockDuration;
	}

	public Integer getServing() {
		return serving;
	}

	public void setServing(final Integer serving) {
		this.serving = serving;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(final String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(final String directions) {
		this.directions = directions;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(final Byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(final Notes notes) {
		this.notes = notes;
		if (notes != null) {
			notes.setRecipe(this);
		}
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"description='" + description + '\'' +
				", prepDuration=" + prepDuration +
				", cookDuration=" + cookDuration +
				", serving=" + serving +
				", sourceUrl='" + sourceUrl + '\'' +
				", sourceName='" + sourceName + '\'' +
				", directions='" + directions + '\'' +
				", difficulty=" + difficulty +
				", notes=" + notes +
				", categories=" + categories +
				", ingredients=" + ingredients +
				"} " + super.toString();
	}
}
