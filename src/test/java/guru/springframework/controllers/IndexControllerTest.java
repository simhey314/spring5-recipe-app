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

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

	@Mock
	RecipeService recioeService;
	@Mock
	Model model;

	IndexController underTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new IndexController(recioeService);
	}

	@Test
	public void testMockMVC() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(underTest).build();

		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void getIndexPage() {
		final String expectedView = "index";
		final Set<Recipe> expectedData = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		expectedData.add(recipe);
		recipe = new Recipe();
		recipe.setId(2L);
		expectedData.add(recipe);
		when(recioeService.getRecipes()).thenReturn(expectedData);
		final ArgumentCaptor<Set<Recipe>> expectedDataCaptor = ArgumentCaptor.forClass(Set.class);
		final String expectedDataKey = "recipes";

		final String actual = underTest.getIndexPage(model);

		assertThat(actual).isEqualTo(expectedView);
		verify(recioeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq(expectedDataKey), expectedDataCaptor.capture());
		final Set<Recipe> actualData = expectedDataCaptor.getValue();
		assertThat(actualData).isEqualTo(expectedData);
	}
}