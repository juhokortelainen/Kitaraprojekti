package hh.swd20.Kitaraprojekti.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tutorialid;
	private String link;

	@OneToMany(mappedBy = "tutorial")
	@JsonIgnoreProperties("tutorial")
	private List<Song> songs;

	public Tutorial() {
		super();
		this.link = null;
	}

	public Tutorial(String link) {
		super();
		this.link = link;
	}

	public Long getTutorialid() {
		return tutorialid;
	}

	public void setTutorialid(Long tutorialid) {
		this.tutorialid = tutorialid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

}
