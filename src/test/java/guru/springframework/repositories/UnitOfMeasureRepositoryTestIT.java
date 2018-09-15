package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() {
	}

	@Test
	public void findByDescription() {

		final Optional<UnitOfMeasure> actual = unitOfMeasureRepository.findByDescription("Tablespoon");

		assertThat(actual.get().getDescription()).isEqualTo("Tablespoon");
	}

	@Test
	public void findByDescriptionNonExisting() {

		final Optional<UnitOfMeasure> actual = unitOfMeasureRepository.findByDescription("none");

		assertThat(actual.isPresent()).isFalse();
	}
}