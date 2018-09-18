package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
	public void testMockMVCSaveRecipe() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);
		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

		mvc.perform(post("/recipe/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some description"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/detail"));
	}

	@Test
	public void testSave() {
		final String expectedView = "redirect:/recipe/2/detail";
		final RecipeCommand recipeData = new RecipeCommand();
		recipeData.setId(2L);
		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeData);

		final String actual = underTest.saveRecipe(recipeData);

		assertThat(actual).isEqualTo(expectedView);
		verify(recipeService, times(1)).saveRecipeCommand(any());
	}

	@Test
	public void testMockMVCDetailById() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();

		mvc.perform(get("/recipe/1/detail"))
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

	@Test
	public void testMockMVCEditById() throws Exception {
		when(recipeService.findRecipeCommandById(1)).thenReturn(new RecipeCommand());
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();

		mvc.perform(get("/recipe/1/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/edit"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testEditById() {
		final RecipeCommand expectedData = new RecipeCommand();
		final long recipeId = 2L;
		final String expectedView = "recipe/edit";
		expectedData.setId(recipeId);
		when(recipeService.findRecipeCommandById(recipeId)).thenReturn(expectedData);
		final ArgumentCaptor<RecipeCommand> expectedDataCaptor = ArgumentCaptor.forClass(RecipeCommand.class);
		final String expectedDataKey = "recipe";

		final String actual = underTest.editById(recipeId, model);

		assertThat(actual).isEqualTo(expectedView);
		verify(recipeService, times(1)).findRecipeCommandById(anyLong());
		verify(model, times(1)).addAttribute(eq(expectedDataKey), expectedDataCaptor.capture());
		final RecipeCommand actualData = expectedDataCaptor.getValue();
		assertThat(actualData).isEqualTo(expectedData);
	}

	@Test
	public void testMockMVCDeleteById() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();

		mvc.perform(get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

	@Test
	public void testDeleteById() {
		final RecipeCommand expectedData = new RecipeCommand();
		final long recipeId = 2L;
		final String expectedView = "redirect:/";

		String actual = underTest.deleteById(recipeId);

		assertThat(actual).isEqualTo(expectedView);
		verify(recipeService, times(1)).deleteById(anyLong());
	}
}