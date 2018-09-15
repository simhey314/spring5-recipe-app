package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

	private Category underTest;

	@Before
	public void setup() {
		underTest = new Category();
	}

	@Test
	public void getId() {
		final Long categoryId = 4L;

		underTest.setId(categoryId);

		assertThat(underTest.getId()).isEqualTo(categoryId);
	}

	@Test
	public void getDescription() {
	}

	@Test
	public void getRecipes() {
	}
}