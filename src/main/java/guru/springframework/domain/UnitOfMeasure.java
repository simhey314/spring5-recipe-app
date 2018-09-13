package guru.springframework.domain;

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends BaseEntity {

	private String description;

	public UnitOfMeasure() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UnitOfMeasure{" +
				"description='" + description + '\'' +
				"} " + super.toString();
	}
}
