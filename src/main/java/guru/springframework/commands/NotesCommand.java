package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

	private Long id;
	private RecipeCommand recipe;
	private String recipeNotes;

}
