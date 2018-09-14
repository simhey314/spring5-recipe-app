package guru.springframework.controllers;

import guru.springframework.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	private RecipeService recipeService;

	public IndexController(final RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(final Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipes());

		return "index";
	}
}
