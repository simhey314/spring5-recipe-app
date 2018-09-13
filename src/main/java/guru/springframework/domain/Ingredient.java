package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Ingredient extends BaseEntity {

	private String descrption;
	private BigDecimal amount;
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unit;
	@ManyToOne
	private Recipe recipe;

	public Ingredient() {
	}

	public UnitOfMeasure getUnit() {
		return unit;
	}

	public void setUnit(final UnitOfMeasure unit) {
		this.unit = unit;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(final String descrption) {
		this.descrption = descrption;
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
				"descrption='" + descrption + '\'' +
				", amount=" + amount +
				", unit=" + unit +
				", recipe=" + recipe +
				"} " + super.toString();
	}
}
