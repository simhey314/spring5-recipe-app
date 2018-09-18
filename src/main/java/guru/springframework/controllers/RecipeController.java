package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private RecipeService recipeService;

	public RecipeController(final RecipeService recioeService) {
		this.recipeService = recioeService;
	}

	@GetMapping("/{id}/detail")
	String detailById(@PathVariable final Long id, final Model model) {
		final Recipe data = recipeService.findRecipeById(id);
		model.addAttribute("recipe", data);
		return "recipe/detail";
	}

	@GetMapping("/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/edit";
	}

	@PostMapping("/save")
	public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedRecipe.getId() + "/detail/";
	}
}
