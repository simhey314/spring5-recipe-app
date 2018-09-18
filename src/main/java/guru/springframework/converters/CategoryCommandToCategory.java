package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	private RecipeCommandToRecipe recipeConverter;

	public CategoryCommandToCategory(final RecipeCommandToRecipe recipeConverter) {
		this.recipeConverter = recipeConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Category convert(final CategoryCommand source) {
		if (source == null) {
			return null;
		}

		Category result = new Category();
		result.setId(source.getId());
		result.setDescription(source.getDescription());

		if (source.getRecipes() != null) {
			source.getRecipes().forEach(recipeCommand -> {
				result.getRecipes().add(recipeConverter.convert(recipeCommand));
			});
		}
		return result;
	}
}
