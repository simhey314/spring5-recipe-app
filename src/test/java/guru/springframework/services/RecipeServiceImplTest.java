package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
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

	public static final long ID = 1l;
	@Mock
	RecipeRepository recipeRepository;

	RecipeServiceImpl underTest;
	@Mock
	private RecipeToRecipeCommand recipeToRecipeCommand;
	@Mock
	private RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
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
		final Recipe expectedRecipe = new Recipe();
		expectedRecipe.setId(ID);
		final Optional<Recipe> expected = Optional.of(expectedRecipe);
		when(recipeRepository.findById(ID)).thenReturn(expected);

		final Recipe actual = underTest.findRecipeById(ID);

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

	@Test
	public void testSaveRecipeCommand() {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(ID);
		Recipe recipeSaved = new Recipe();
		recipeSaved.setId(ID);
		RecipeCommand expected = new RecipeCommand();
		expected.setId(ID);
		when(recipeCommandToRecipe.convert(any(RecipeCommand.class))).thenReturn(recipeSaved);
		when(recipeRepository.save(recipeSaved)).thenReturn(recipeSaved);
		when(recipeToRecipeCommand.convert(recipeSaved)).thenReturn(expected);

		RecipeCommand actual = underTest.saveRecipeCommand(recipeCommand);

		assertThat(actual.getId()).isEqualTo(ID);
		assertThat(actual).isNotEqualTo(recipeCommand);
		verify(recipeRepository, times(1)).save(any(Recipe.class));
		verify(recipeCommandToRecipe, times(1)).convert(any(RecipeCommand.class));
		verify(recipeToRecipeCommand, times(1)).convert(any(Recipe.class));
	}
}