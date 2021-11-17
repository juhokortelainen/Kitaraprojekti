package hh.swd20.Kitaraprojekti.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Kitaraprojekti.domain.DifficultyRepository;
import hh.swd20.Kitaraprojekti.domain.Song;
import hh.swd20.Kitaraprojekti.domain.SongRepository;
import hh.swd20.Kitaraprojekti.domain.TuningRepository;

@CrossOrigin(origins = "*")
@Controller
public class SongController {

	@Autowired
	private SongRepository songrepository;
	@Autowired
	private TuningRepository tuningrepository;
	@Autowired
	private DifficultyRepository difficultyrepository;

	/*********** RESTFUL SERVICES ************************/

	// REST to get all songs
	@RequestMapping(value = "/api/songs", method = RequestMethod.GET)
	public @ResponseBody List<Song> bookListRest() {
		return (List<Song>) songrepository.findAll();
	}

	// REST to get song by id
	@RequestMapping(value = "/api/songs/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Song> findSongRest(@PathVariable("id") Long id) {
		return songrepository.findById(id);
	}

	/*****************************************************/

	// get all songs from database
	@RequestMapping(value = "/songlist", method = RequestMethod.GET)
	public String songList(Model model) {
		model.addAttribute("songs", songrepository.findAll());
		return "songlist";
	}

	// creating an empty form to add a song
	@RequestMapping(value = "/addsong", method = RequestMethod.GET)
	public String addSong(Model model) {
		model.addAttribute("song", new Song());
		model.addAttribute("tunings", tuningrepository.findAll());
		model.addAttribute("difficulties", difficultyrepository.findAll());
		return "addsong";
	}

	// saving added song
	@RequestMapping(value = "/savesong", method = RequestMethod.POST)
	public String saveSong(@Valid Song song, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			model.addAttribute("tunings", tuningrepository.findAll());
			model.addAttribute("difficulties", difficultyrepository.findAll());
			return "addsong";
		} else {
			songrepository.save(song);
			return "redirect:songlist";
		}
	}

	// delete song by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletesong/{id}", method = RequestMethod.GET)
	public String deleteSong(@PathVariable("id") Long id, Model model) {
		songrepository.deleteById(id);
		return "redirect:../songlist";
	}

	// edit song by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/editsong/{id}", method = RequestMethod.GET)
	public String editSong(@PathVariable("id") Long id, Model model) {
		model.addAttribute("song", songrepository.findById(id).get());
		model.addAttribute("tunings", tuningrepository.findAll());
		model.addAttribute("difficulties", difficultyrepository.findAll());
		return "editsong";
	}
}
