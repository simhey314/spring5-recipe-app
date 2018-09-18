package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryCommandToCategoryTest {

	private static final Long ID = 314L;
	private static final String DESCRIPTION = "description";
	@Mock
	private RecipeCommandToRecipe recipeCommandToRecipe;

	private CategoryCommandToCategory underTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new CategoryCommandToCategory(recipeCommandToRecipe);
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
		RecipeCommand recipeCommand = new RecipeCommand();
		categoryCommand.getRecipes().add(recipeCommand);
		when(recipeCommandToRecipe.convert(recipeCommand)).thenReturn(new Recipe());

		Category actual = underTest.convert(categoryCommand);

		assertThat(actual.getId()).isEqualTo(ID);
		assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
		assertThat(actual.getRecipes().size()).isEqualTo(1);
		verify(recipeCommandToRecipe, times(1)).convert(any(RecipeCommand.class));
	}
}