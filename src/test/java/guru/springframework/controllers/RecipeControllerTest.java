package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	@Mock
	Model model;

	RecipeController underTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new RecipeController(recipeService);
	}

	@Test
	public void testMockMVC() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();

		mvc.perform(get("/recipe/detail/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/detail"));
	}

	@Test
	public void testDetailById() {
		final String expectedView = "recipe/detail";
		final Recipe expectedData = new Recipe();
		final long recipeId = 1L;
		expectedData.setId(recipeId);
		when(recipeService.findRecipeById(recipeId)).thenReturn(expectedData);
		final ArgumentCaptor<Recipe> expectedDataCaptor = ArgumentCaptor.forClass(Recipe.class);
		final String expectedDataKey = "recipe";

		final String actual = underTest.detailById(recipeId, model);

		assertThat(actual).isEqualTo(expectedView);
		verify(recipeService, times(1)).findRecipeById(anyLong());
		verify(model, times(1)).addAttribute(eq(expectedDataKey), expectedDataCaptor.capture());
		final Recipe actualData = expectedDataCaptor.getValue();
		assertThat(actualData).isEqualTo(expectedData);
	}
}