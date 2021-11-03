package hh.swd20.Kitaraprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Kitaraprojekti.domain.DifficultyRepository;
import hh.swd20.Kitaraprojekti.domain.Song;
import hh.swd20.Kitaraprojekti.domain.SongRepository;
import hh.swd20.Kitaraprojekti.domain.TuningRepository;
import hh.swd20.Kitaraprojekti.domain.TutorialRepository;

@Controller
public class SongController {
	
	@Autowired private SongRepository songrepository;
	@Autowired private TuningRepository tuningrepository;
	@Autowired private DifficultyRepository difficultyrepository;
	@Autowired private TutorialRepository tutorialrepository;
	
	// get all songs from database
	@RequestMapping(value = "/songlist", method = RequestMethod.GET)
	public String songList(Model model) {
		model.addAttribute("songs", songrepository.findAll());
		return "songlist";
	}
	
	// creating an empty form to add a song
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addSong(Model model) {
		model.addAttribute("song", new Song());
		model.addAttribute("tunings", tuningrepository.findAll());
		model.addAttribute("difficulties", difficultyrepository.findAll());
		model.addAttribute("tutorials", tutorialrepository.findAll());
		return "addsong";
	}
	
	// saving added song
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSong(Song song) {
		songrepository.save(song);
		return "redirect:songlist";
	}

}
