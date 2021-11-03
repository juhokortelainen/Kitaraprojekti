package hh.swd20.Kitaraprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long songid;
	private String title;
	private String artist;
	
	@ManyToOne
	@JsonIgnoreProperties("songs")
	@JoinColumn(name = "tuningid")
	private Tuning tuning;
	
	@ManyToOne
	@JsonIgnoreProperties("songs")
	@JoinColumn(name = "difficultyid")
	private Difficulty difficulty;
	
	@ManyToOne
	@JsonIgnoreProperties("songs")
	@JoinColumn(name = "tutorialid")
	private Tutorial tutorial;

	public Song() {
		super();
		this.title = null;
		this.artist = null;
		this.tuning = null;
		this.difficulty = null;
		this.tutorial = null;
	}
	
	public Song(String title, String artist, Tuning tuning, Difficulty difficulty, Tutorial tutorial) {
		super();
		this.title = title;
		this.artist = artist;
		this.tuning = tuning;
		this.difficulty = difficulty;
		this.tutorial = tutorial;
	}

	public Long getSongid() {
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Tuning getTuning() {
		return tuning;
	}

	public void setTuning(Tuning tuning) {
		this.tuning = tuning;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	@Override
	public String toString() {
		return "Song [songid=" + songid + ", title=" + title + ", artist=" + artist + ", tuning=" + tuning
				+ ", difficulty=" + difficulty + ", tutorial=" + tutorial + "]";
	}
	
	

}
