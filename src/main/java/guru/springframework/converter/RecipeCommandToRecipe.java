package guru.springframework.converter;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	@Override
	public Recipe convert(final RecipeCommand source) {
		return null;
	}
}
