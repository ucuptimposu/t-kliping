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

import com.timposu.tkliping.model.Kategori;
import com.timposu.tkliping.service.KategoriService;

@Controller
@RequestMapping(value = "/kategori")
public class KategoriController {

	@Autowired
	private KategoriService ks;
	
	@GetMapping("/")
	public String listKecamatan(Model model) {
		model.addAttribute("daftarKategori", ks.list());
		return "kategori/list";
	}
	
	@GetMapping("/form")
	public String formKategori(@RequestParam(value = "id", required = false) Integer id, 
			Model m) {
		m.addAttribute("daftarKategori", ks.list());
		m.addAttribute("kategori", new Kategori());
		
		if (id != null) {
			Kategori k = ks.getKategori(id);
			if (k != null) {
				m.addAttribute("kategori", k);
			}
		}
		return "kategori/form";
	}
	
	@PostMapping("/form")
	public String prosesForm(Model m, @Valid @ModelAttribute Kategori k,
			BindingResult result) {
		m.addAttribute("daftarKategori", ks.list());
		
		if (result.hasErrors()) {
			return "kategori/form";
		}
		ks.save(k);
		return "redirect:/kategori/";
	}
	
	@GetMapping("/hapus")
	public String hapusKategori(@RequestParam(value = "id") Integer id) {
		ks.delete(id);
		return "redirect:/kategori/";
	}
}
