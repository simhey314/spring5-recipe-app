package guru.springframework.converter;

import guru.springframework.command.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	RecipeToRecipeCommand recipeConverter;

	public CategoryToCategoryCommand(final RecipeToRecipeCommand recipeConverter) {
		this.recipeConverter = recipeConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(final Category source) {
		if (source == null) {
			return null;
		}

		final CategoryCommand result = new CategoryCommand();
		result.setDescription(source.getDescription());
		result.setId(source.getId());

		if (source.getRecipes() != null) {
			source.getRecipes().forEach(recipe -> result.getRecipes().add(recipeConverter.convert(recipe)));
		}

		return result;
	}
}
