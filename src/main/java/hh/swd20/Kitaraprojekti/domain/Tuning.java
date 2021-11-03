package hh.swd20.Kitaraprojekti.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tuning {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tuningid;
	private String name;
	
	@OneToMany(mappedBy = "tuning")
	@JsonIgnoreProperties("tuning")
	private List<Song> songs;

	public Tuning() {
		super();
		this.name = null;
	}
	
	public Tuning(String name) {
		super();
		this.name = name;
	}

	public Long getTuningid() {
		return tuningid;
	}

	public void setTuningid(Long tuningid) {
		this.tuningid = tuningid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
