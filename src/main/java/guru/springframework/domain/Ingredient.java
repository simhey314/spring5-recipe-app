package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Ingredient extends BaseEntity {

	private String description;
	private BigDecimal amount;
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unit;
	@ManyToOne
	private Recipe recipe;

	public Ingredient() {
	}

	public Ingredient(final String description, final BigDecimal amount, final UnitOfMeasure unit, final Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.unit = unit;
		this.recipe = recipe;
	}

	public UnitOfMeasure getUnit() {
		return unit;
	}

	public void setUnit(final UnitOfMeasure unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(final Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Ingredient{" +
				"description='" + description + '\'' +
				", amount=" + amount +
				", unit=" + unit +
				"} " + super.toString();
	}
}
