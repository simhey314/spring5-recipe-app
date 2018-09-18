package guru.springframework.services;

import guru.springframework.command.RecipeCommand;
import guru.springframework.converter.RecipeCommandToRecipe;
import guru.springframework.converter.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private RecipeCommandToRecipe recipeCommandToRecipe;
	private RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(final RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");

		final Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	@Nullable
	public Recipe findRecipeById(final Long id) {
		log.debug("get a recipe by id: {}", id);

		final Optional<Recipe> recipeOptional = recipeRepository.findById(id);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("There is no recipe with id: " + id);
		}

		return recipeOptional.orElse(null);
	}

	@Override
	@Transactional
	public RecipeCommand save(final RecipeCommand recipeCommand) {
		Recipe detached = recipeCommandToRecipe.convert(recipeCommand);
		Recipe saved = recipeRepository.save(detached);
		log.debug("Saved recipe object: {}", saved);
		return recipeToRecipeCommand.convert(saved);
	}
}
