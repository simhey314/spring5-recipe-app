package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Transactional
	@Override
	public UnitOfMeasure convert(final UnitOfMeasureCommand source) {
		if (source == null) {
			return null;
		}

		UnitOfMeasure result = new UnitOfMeasure();
		result.setId(source.getId());
		result.setDescription(source.getDescription());

		return result;
	}
}
