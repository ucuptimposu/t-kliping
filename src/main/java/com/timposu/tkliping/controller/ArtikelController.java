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

import com.timposu.tkliping.model.Artikel;
import com.timposu.tkliping.model.Rubrik;
import com.timposu.tkliping.service.ArtikelService;
import com.timposu.tkliping.service.RubrikService;

@Controller
@RequestMapping(value = "/artikel")
public class ArtikelController {
	
	@Autowired
	private ArtikelService as;
	
	@Autowired
	private RubrikService rs;
	
	@GetMapping("/")
	public String listArtikel(Model model) {
		model.addAttribute("daftarArtikel", as.list());
		model.addAttribute("daftarRubrik", rs.list());
		return "artikel/list";
	}
	
	@GetMapping("/form")
	public String formArtikel(@RequestParam(value = "id", 
			required = false) String id, Model m) {
		
		m.addAttribute("artikel", new Artikel());
		
		if (id != null) {
			Artikel a = as.getArtikel(id);
			if (a != null) {
				m.addAttribute("artikel", a);
			}
		}
		return "artikel/form";
	}
	
	@PostMapping("/form")
	public String prosesForm(Model m, @Valid @ModelAttribute Artikel a,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "artikel/form";
		}
		
		if (a.getId() == null || a.getId().isEmpty()) {
			as.save(a);
		} else {
			as.update(a);
		}
		return "redirect:/artikel/";
	}	
	
}
