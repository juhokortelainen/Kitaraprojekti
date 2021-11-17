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

import hh.swd20.Kitaraprojekti.domain.Difficulty;
import hh.swd20.Kitaraprojekti.domain.DifficultyRepository;

@CrossOrigin(origins = "*")
@Controller
public class DifficultyController {

	@Autowired
	private DifficultyRepository difficultyrepository;

	/*********** RESTFUL SERVICES ************************/

	// REST to get all difficulties
	@RequestMapping(value = "/api/difficulties", method = RequestMethod.GET)
	public @ResponseBody List<Difficulty> difficultyListRest() {
		return (List<Difficulty>) difficultyrepository.findAll();
	}

	// REST to get difficulty by id
	@RequestMapping(value = "/api/difficulties/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Difficulty> findDifficultyRest(@PathVariable("id") Long id) {
		return difficultyrepository.findById(id);
	}

	/*****************************************************/

	// get all difficulties from database
	@RequestMapping(value = "/difficultylist", method = RequestMethod.GET)
	public String difficultyList(Model model) {
		model.addAttribute("difficulties", difficultyrepository.findAll());
		return "difficultylist";
	}

	// creating an empty form to add a difficulty
	@RequestMapping(value = "/adddifficulty", method = RequestMethod.GET)
	public String addDifficulty(Model model) {
		model.addAttribute("difficulty", new Difficulty());
		return "adddifficulty";
	}

	// saving added difficulty
	@RequestMapping(value = "/savedifficulty", method = RequestMethod.POST)
	public String saveDifficulty(@Valid Difficulty difficulty, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			return "adddifficulty";
		} else {
			difficultyrepository.save(difficulty);
			return "redirect:difficultylist";
		}
	}

	// delete difficulty by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletedifficulty/{id}", method = RequestMethod.GET)
	public String deleteDifficulty(@PathVariable("id") Long id, Model model) {
		difficultyrepository.deleteById(id);
		return "redirect:../difficultylist";
	}

	// edit difficulty by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/editdifficulty/{id}", method = RequestMethod.GET)
	public String editDifficulty(@PathVariable("id") Long id, Model model) {
		model.addAttribute("difficulty", difficultyrepository.findById(id).get());
		return "editdifficulty";
	}

}
