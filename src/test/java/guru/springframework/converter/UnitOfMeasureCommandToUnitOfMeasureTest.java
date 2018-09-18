package guru.springframework.converter;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	private static final String DESCRIPTION = "description";
	private static final Long ID = 3141L;
	private UnitOfMeasureCommandToUnitOfMeasure underTest;

	@Before
	public void setUp() {
		underTest = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testConvertNull() {

		UnitOfMeasure actual = underTest.convert(null);

		assertThat(actual).isNull();
	}

	@Test
	public void testConvertEmptyObject() {

		UnitOfMeasure actual = underTest.convert(new UnitOfMeasureCommand());

		assertThat(actual).isEqualToComparingFieldByField(new UnitOfMeasure());
	}

	@Test
	public void testConvert() {
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(ID);
		uomCommand.setDescription(DESCRIPTION);

		UnitOfMeasure actual = underTest.convert(uomCommand);

		assertThat(actual.getId()).isEqualTo(ID);
		assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
	}
}
