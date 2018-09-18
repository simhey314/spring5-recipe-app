package guru.springframework.converter;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Transactional
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(final UnitOfMeasure source) {
		if (source == null) {
			return null;
		}
		UnitOfMeasureCommand result = new UnitOfMeasureCommand();
		result.setId(source.getId());
		result.setDescription(source.getDescription());

		return result;
	}
}
