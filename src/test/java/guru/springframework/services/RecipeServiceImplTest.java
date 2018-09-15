package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void getRecipes() {
		final Set<Recipe> expected = new HashSet<>();
		expected.add(new Recipe());
		when(recipeRepository.findAll()).thenReturn(expected);

		final Set<Recipe> actual = underTest.getRecipes();

		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(expected);
		verify(recipeRepository, times(1)).findAll();
	}
}