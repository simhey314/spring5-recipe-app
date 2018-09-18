package guru.springframework.converter;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
	@Override
	public RecipeCommand convert(final Recipe source) {
		return null;
	}
}
