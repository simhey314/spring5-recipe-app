package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	private static final String DESCRIPTION = "description";
	private static final Long ID = 3141L;
	private UnitOfMeasureToUnitOfMeasureCommand underTest;

	@Before
	public void setUp() {
		underTest = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testConvertNull() {

		UnitOfMeasureCommand actual = underTest.convert(null);

		assertThat(actual).isNull();
	}

	@Test
	public void testConvertEmptyObject() {

		UnitOfMeasureCommand actual = underTest.convert(new UnitOfMeasure());

		assertThat(actual).isEqualToComparingFieldByField(new UnitOfMeasureCommand());
	}

	@Test
	public void testConvert() {
		UnitOfMeasure uomCommand = new UnitOfMeasure();
		uomCommand.setId(ID);
		uomCommand.setDescription(DESCRIPTION);

		UnitOfMeasureCommand actual = underTest.convert(uomCommand);

		assertThat(actual.getId()).isEqualTo(ID);
		assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
	}
}
