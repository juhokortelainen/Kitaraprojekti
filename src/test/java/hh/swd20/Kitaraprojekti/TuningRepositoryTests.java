package hh.swd20.Kitaraprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Kitaraprojekti.domain.Tuning;
import hh.swd20.Kitaraprojekti.domain.TuningRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TuningRepositoryTests {

	@Autowired
	private TuningRepository tuningRepository;

	// test to create a new tuning
	@Test
	public void createNewTuning() {
		Tuning tuning = new Tuning("Drop B");
		tuningRepository.save(tuning);
		assertThat(tuning.getTuningid()).isNotNull();
	}

	// test to delete a tuning by id
	@Test
	public void deleteTuning() {
		Tuning tuning = tuningRepository.findById(Long.valueOf(2)).get();
		tuningRepository.delete(tuning);
		Optional<Tuning> deleteTuning = tuningRepository.findById(Long.valueOf(2));
		assertThat(deleteTuning).isEmpty();
	}

	// test to search a tuning by id
	@Test
	public void searchTuning() {
		Tuning tuning = new Tuning("Half step down");
		tuningRepository.save(tuning);
		assertThat(tuningRepository.findById(tuning.getTuningid())).isNotNull();

	}
}
