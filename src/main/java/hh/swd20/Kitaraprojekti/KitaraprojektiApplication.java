package hh.swd20.Kitaraprojekti;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Kitaraprojekti.domain.Difficulty;
import hh.swd20.Kitaraprojekti.domain.DifficultyRepository;
import hh.swd20.Kitaraprojekti.domain.Song;
import hh.swd20.Kitaraprojekti.domain.SongRepository;
import hh.swd20.Kitaraprojekti.domain.Tuning;
import hh.swd20.Kitaraprojekti.domain.TuningRepository;
import hh.swd20.Kitaraprojekti.domain.Tutorial;
import hh.swd20.Kitaraprojekti.domain.TutorialRepository;

@SpringBootApplication
public class KitaraprojektiApplication {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(KitaraprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KitaraprojektiApplication.class, args);
	}

	// creating testdata to h2-database
	@Bean
	public CommandLineRunner songDemo(SongRepository songrepository, TuningRepository tuningrepository,
			DifficultyRepository difficultyrepository, TutorialRepository tutorialrepository) {
		return (args) -> {
			// create a couple of tunings
			log.info("Save some sample tunings");
			Tuning tuning1 = new Tuning("Standard");
			tuningrepository.save(tuning1);
			Tuning tuning2 = new Tuning("Drop D");
			tuningrepository.save(tuning2);
			
			// log tunings from database
			log.info("Fetch all tunings");
			for (Tuning tuning : tuningrepository.findAll()) {
				log.info(tuning.toString());
			}
			
			// create a few difficulties
			log.info("Save some sample difficulties");
			Difficulty difficulty1 = new Difficulty("Easy");
			difficultyrepository.save(difficulty1);
			Difficulty difficulty2 = new Difficulty("Medium");
			difficultyrepository.save(difficulty2);
			Difficulty difficulty3 = new Difficulty("Hard");
			difficultyrepository.save(difficulty3);
			
			// log difficulties from database
			log.info("Fetch all difficulties");
			for (Difficulty difficulty : difficultyrepository.findAll()) {
				log.info(difficulty.toString());
			}
			
			// create an example link
			log.info("Save a sample link");
			Tutorial tutorial1 = new Tutorial("link to video lesson");
			tutorialrepository.save(tutorial1);
			
			// log tutorial link from database
			log.info("Fetch the tutorial link");
			for (Tutorial tutorial : tutorialrepository.findAll()) {
				log.info(tutorial.toString());
			}
			
			// create a few songs to h2-database
			log.info("Add a few songs");
			songrepository.save(new Song("Fade To Black", "Metallica", tuning1, difficulty2, tutorial1));
			songrepository.save(new Song("Unholy Confessions", "Avenged Sevenfold", tuning2, difficulty1, tutorial1));
			songrepository.save(new Song("Master of Puppets", "Metallica", tuning1, difficulty3, tutorial1));
			
			// log songs from the database
			log.info("Fetch all songs");
			for (Song song : songrepository.findAll()) {
				log.info(song.toString());
			}
			
		};
	}

}
