package hh.swd20.Kitaraprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Kitaraprojekti.domain.Difficulty;
import hh.swd20.Kitaraprojekti.domain.DifficultyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DifficultyRepositoryTests {

	@Autowired
	private DifficultyRepository difficultyRepository;

	// test to create a new difficulty
	@Test
	public void createNewDifficulty() {
		Difficulty difficulty = new Difficulty("Impossible");
		difficultyRepository.save(difficulty);
		assertThat(difficulty.getDifficultyid()).isNotNull();
	}

	// test to delete a difficulty by id
	@Test
	public void deleteDifficulty() {
		Difficulty difficulty = difficultyRepository.findById(Long.valueOf(4)).get();
		difficultyRepository.delete(difficulty);
		Optional<Difficulty> deleteDifficulty = difficultyRepository.findById(Long.valueOf(4));
		assertThat(deleteDifficulty).isEmpty();
	}

	// test to search a difficulty by id
	@Test
	public void searchDifficulty() {
		Difficulty difficulty = new Difficulty("Impossible+");
		difficultyRepository.save(difficulty);
		assertThat(difficultyRepository.findById(difficulty.getDifficultyid())).isNotNull();

	}
}
