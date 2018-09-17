package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private RecipeService recioeService;

	public RecipeController(final RecipeService recioeService) {
		this.recioeService = recioeService;
	}

	@GetMapping("detail/{id}")
	String showById(@PathVariable final Long id, final Model model) {
		final Recipe data = recioeService.findRecipeById(id);
		model.addAttribute("recipe", data);
		return "recipe/detail";
	}
}
