package hh.swd20.Kitaraprojekti.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Difficulty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long difficultyid;
	private String rating;

	@OneToMany(mappedBy = "difficulty")
	@JsonIgnoreProperties("difficulty")
	private List<Song> songs;

	public Difficulty() {
		super();
		this.rating = null;
	}

	public Difficulty(String rating) {
		super();
		this.rating = rating;
	}

	public Long getDifficultyid() {
		return difficultyid;
	}

	public void setDifficultyid(Long difficultyid) {
		this.difficultyid = difficultyid;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
