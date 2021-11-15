package hh.swd20.Kitaraprojekti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Kitaraprojekti.web.DifficultyController;
import hh.swd20.Kitaraprojekti.web.SongController;
import hh.swd20.Kitaraprojekti.web.TuningController;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KitaraprojektiApplicationTests {
	
	@Autowired private SongController songController;
	@Autowired private TuningController tuningController;
	@Autowired private DifficultyController difficultyController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(songController).isNotNull();
		assertThat(tuningController).isNotNull();
		assertThat(difficultyController).isNotNull();
	}
	

}
