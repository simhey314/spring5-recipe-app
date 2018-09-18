package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryCommandToCategoryTest {

	private static final Long ID = 314L;
	private static final String DESCRIPTION = "description";

	private CategoryCommandToCategory underTest;

	@Before
	public void setUp() {
		underTest = new CategoryCommandToCategory();
	}

	@Test
	public void testConvertNull() {

		Category actual = underTest.convert(null);

		assertThat(actual).isNull();
	}

	@Test
	public void testConvertEmtpyObject() {

		Category actual = underTest.convert(new CategoryCommand());

		assertThat(actual).isEqualToComparingFieldByField(new Category());
	}

	@Test
	public void testConvert() {
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(ID);
		categoryCommand.setDescription(DESCRIPTION);

		Category actual = underTest.convert(categoryCommand);

		assertThat(actual.getId()).isEqualTo(ID);
		assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
	}
}