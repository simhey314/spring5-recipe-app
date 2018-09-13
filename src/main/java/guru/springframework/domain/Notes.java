package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Notes extends BaseEntity {

	@OneToOne
	private Recipe recipe;
	@Lob
	private String notes;

	public Notes() {
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(final Recipe recipe) {
		this.recipe = recipe;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Notes{" +
				", recipe=" + recipe +
				", notes='" + notes + '\'' +
				"} " + super.toString();
	}
}
