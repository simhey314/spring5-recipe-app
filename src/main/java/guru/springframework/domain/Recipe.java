package guru.springframework.domain;

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
import java.util.Set;

@Entity
public class Recipe extends BaseEntity {

	private String description;
	private Integer prepDuration;
	private Integer cockDuration;
	private Integer serving;
	private String sourceUrl;
	private String sourceName;
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
	private Set<Category> categories;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients;

	public Recipe() {
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(final Set<Category> categories) {
		this.categories = categories;
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

	public Integer getCockDuration() {
		return cockDuration;
	}

	public void setCockDuration(final Integer cockDuration) {
		this.cockDuration = cockDuration;
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
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"description='" + description + '\'' +
				", prepDuration=" + prepDuration +
				", cockDuration=" + cockDuration +
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
