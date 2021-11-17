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

import hh.swd20.Kitaraprojekti.domain.Tuning;
import hh.swd20.Kitaraprojekti.domain.TuningRepository;

@CrossOrigin(origins = "*")
@Controller
public class TuningController {

	@Autowired
	private TuningRepository tuningrepository;

	/*********** RESTFUL SERVICES ************************/

	// REST to get all tunings
	@RequestMapping(value = "/tunings", method = RequestMethod.GET)
	public @ResponseBody List<Tuning> tuningListRest() {
		return (List<Tuning>) tuningrepository.findAll();
	}

	// REST to get tuning by id
	@RequestMapping(value = "/tunings/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Tuning> findTuningRest(@PathVariable("id") Long id) {
		return tuningrepository.findById(id);
	}

	/*****************************************************/

	// get all tunings from database
	@RequestMapping(value = "/tuninglist", method = RequestMethod.GET)
	public String tuningList(Model model) {
		model.addAttribute("tunings", tuningrepository.findAll());
		return "tuninglist";
	}

	// creating an empty form to add a tuning
	@RequestMapping(value = "/addtuning", method = RequestMethod.GET)
	public String addTuning(Model model) {
		model.addAttribute("tuning", new Tuning());
		return "addtuning";
	}

	// saving added tuning
	@RequestMapping(value = "/savetuning", method = RequestMethod.POST)
	public String saveTuning(@Valid Tuning tuning, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			return "addtuning";
		} else {
			tuningrepository.save(tuning);
			return "redirect:tuninglist";
		}
	}

	// delete tuning by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletetuning/{id}", method = RequestMethod.GET)
	public String deleteTuning(@PathVariable("id") Long id, Model model) {
		tuningrepository.deleteById(id);
		return "redirect:../tuninglist";
	}

	// edit tuning by id
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@RequestMapping(value = "/edittuning/{id}", method = RequestMethod.GET)
	public String editTuning(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tuning", tuningrepository.findById(id).get());
		return "edittuning";
	}

}
