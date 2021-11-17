package hh.swd20.Kitaraprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Kitaraprojekti.domain.Song;
import hh.swd20.Kitaraprojekti.domain.SongRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SongRepositoryTests {
	
	@Autowired
	private SongRepository songRepository;
	
	// test to create a new song
	@Test
	public void createNewSong() {
		Song song = new Song("Testilaulu", "Juho", "ei linkkiä", null, null);
		songRepository.save(song);
		assertThat(song.getSongid()).isNotNull();
	}
	
	// test to delete a song by id
	@Test
	public void deleteSong() {
		Song song = songRepository.findById(Long.valueOf(6)).get();
		songRepository.delete(song);
		Optional<Song> deleteSong = songRepository.findById(Long.valueOf(6));
		assertThat(deleteSong).isEmpty();
	}
	
	// test to search a song by id
	@Test
	public void searchSong() {
		Song song = new Song("Etsintälaulu", "Kansanlauluja", "linkki tulossa", null, null);
		songRepository.save(song);
		assertThat(songRepository.findById(song.getSongid())).isNotNull();
	}

}
