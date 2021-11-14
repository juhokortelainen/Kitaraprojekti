package hh.swd20.Kitaraprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long songid;
	
	@NotEmpty(message = "Please enter a name for the song")
	private String title;
	private String artist;
	private String tutorial;
	
	@ManyToOne
	@JsonIgnoreProperties("songs")
	@JoinColumn(name = "tuningid")
	private Tuning tuning;
	
	@ManyToOne
	@JsonIgnoreProperties("songs")
	@JoinColumn(name = "difficultyid")
	private Difficulty difficulty;

	public Song() {
		super();
		this.title = null;
		this.artist = null;
		this.tutorial = null;
		this.tuning = null;
		this.difficulty = null;
	}
	
	public Song(String title, String artist, String tutorial, Tuning tuning, Difficulty difficulty) {
		super();
		this.title = title;
		this.artist = artist;
		this.tutorial = tutorial;
		this.tuning = tuning;
		this.difficulty = difficulty;
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
	public String getTutorial() {
		return tutorial;
	}
	
	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
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

	@Override
	public String toString() {
		return "Song [songid=" + songid + ", title=" + title + ", artist=" + artist + ", tutorial=" + tutorial
				+ ", tuning=" + this.getTuning() + ", difficulty=" + this.getDifficulty() + "]";
	}


	
	
	

}
