package com.timposu.tkliping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.timposu.tkliping.model.Rubrik;
import com.timposu.tkliping.service.RubrikService;

@Controller
@RequestMapping(value = "/rubrik")
public class RubrikController {

	@Autowired
	private RubrikService rs;
	
	@GetMapping("/")
	public String listKecamatan(Model model) {
		model.addAttribute("daftarRubrik", rs.list());
		return "rubrik/list";
	}
	
	@GetMapping("/form")
	public String formKategori(@RequestParam(value = "id", 
			required = false) Integer id, Model m) {
		
		m.addAttribute("rubrik", new Rubrik());
		
		if (id != null) {
			Rubrik k = rs.getRubrik(id);
			if (k != null) {
				m.addAttribute("rubrik", k);
			}
		}
		return "rubrik/form";
	}
	
	@PostMapping("/form")
	public String prosesForm(Model m, @Valid @ModelAttribute Rubrik r,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "rubrik/form";
		}
		rs.save(r);
		return "redirect:/rubrik/";
	}
	
	@GetMapping("/hapus")
	public String hapusRubrik(@RequestParam(value = "id") Integer id) {
		rs.delete(id);
		return "redirect:/rubrik/";
	}
}
