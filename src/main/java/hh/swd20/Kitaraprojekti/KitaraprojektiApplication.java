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
import hh.swd20.Kitaraprojekti.domain.User;
import hh.swd20.Kitaraprojekti.domain.UserRepository;

@SpringBootApplication
public class KitaraprojektiApplication {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(KitaraprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KitaraprojektiApplication.class, args);
	}

	// creating testdata to h2-database
	@Bean
	public CommandLineRunner songDemo(SongRepository songrepository, TuningRepository tuningrepository,
			DifficultyRepository difficultyrepository, UserRepository userrepository) {
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
			
			// create a few songs to h2-database
			log.info("Add a few songs");
			songrepository.save(new Song("Fade To Black", "Metallica", "https://www.youtube.com/watch?v=T10NnePvkuI", tuning1, difficulty2));
			songrepository.save(new Song("Unholy Confessions", "Avenged Sevenfold", "https://youtu.be/cpxVJvDcpIU", tuning2, difficulty1));
			songrepository.save(new Song("Master of Puppets", "Metallica", "https://youtu.be/FvVrCKgEu4s", tuning1, difficulty3));
			
			// log songs from the database
			log.info("Fetch all songs");
			for (Song song : songrepository.findAll()) {
				log.info(song.toString());
			}
			
			// create users: admin/admin and user/user
			User user1 = new User("user", "$2a$10$oD37WvChWlWZJwFa.9cmXeVM9ZWW77bebZt6JvHwwdqddHf1dN/mu", "user@user.mail", "USER");
			User user2 = new User("admin", "$2a$10$4ItnrA0QYwJx8MeXRQ7fWO6AHu.9MIaXXKvniBsePnK72DHdNIaaq", "admin@admin.mail", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);
			
			// log users from the database
			log.info("fetch all users");
			for (User user : userrepository.findAll()) {
				log.info(user.toString());
			}
			
		};
	}

}
