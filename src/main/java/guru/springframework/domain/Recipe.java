package guru.springframework.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepDuration;
	private Integer cockDuration;
	private Integer serving;
	private String sourceUrl;
	private String sourceName;
	private String directions;
	//private Difficulty difficulty;
	@Lob
	private Byte[] image;
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients;

	public Recipe() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
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
				", notes=" + notes +
				'}';
	}
}
