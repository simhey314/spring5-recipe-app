package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;
	RecipeServiceImpl underTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void testGetRecipes() {
		final Set<Recipe> expected = new HashSet<>();
		expected.add(new Recipe());
		when(recipeRepository.findAll()).thenReturn(expected);

		final Set<Recipe> actual = underTest.getRecipes();

		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(expected);
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	public void testGetRecipeById() {
		final Long recipeId = 1l;
		final Recipe expectedRecipe = new Recipe();
		expectedRecipe.setId(recipeId);
		final Optional<Recipe> expected = Optional.of(expectedRecipe);
		when(recipeRepository.findById(recipeId)).thenReturn(expected);

		final Recipe actual = underTest.findRecipeById(recipeId);

		assertThat(actual).isEqualTo(expectedRecipe);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test()
	public void testGetRecipeByIdNotExist() {
		final Optional<Recipe> expected = Optional.empty();
		when(recipeRepository.findById(anyLong())).thenReturn(expected);

		final Throwable actualException = catchThrowable(() -> underTest.findRecipeById(-1l));

		assertThat(actualException).isInstanceOf(RuntimeException.class)
				.hasMessageStartingWith("There is no recipe with id:");
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}
}