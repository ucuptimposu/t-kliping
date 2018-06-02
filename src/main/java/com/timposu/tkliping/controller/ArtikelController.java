package com.timposu.tkliping.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.timposu.tkliping.model.Artikel;
import com.timposu.tkliping.model.Files;
import com.timposu.tkliping.property.ArtikelProperty;
import com.timposu.tkliping.service.ArtikelService;
import com.timposu.tkliping.service.FilesService;
import com.timposu.tkliping.service.RubrikService;

@Controller
@RequestMapping(value = "/artikel")
public class ArtikelController {
	
	@Autowired
	private ArtikelService as;
	
	@Autowired
	private RubrikService rs;
	
	@Autowired
	private FilesService fs;
	
	private final String FOLDER_TUJUAN = "/uploads";
	
	private static final Logger LOGGER = LoggerFactory.
			getLogger(ArtikelController.class);
	
	//	Konventer String ke Date
	@InitBinder
	public void initConventer(WebDataBinder binder) {
		binder.registerCustomEditor(Artikel.class, new ArtikelProperty(as));
        binder.registerCustomEditor(Date.class, 
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@GetMapping("/")
	public String listArtikel(Model model) {
		model.addAttribute("daftarArtikel", as.list());
		model.addAttribute("daftarRubrik", rs.list());
		return "artikel/list";
	}
	
	@GetMapping("/form")
	public String formArtikel(Model m) {
		
		m.addAttribute("artikel", new Artikel());
		m.addAttribute("daftarRubrik", rs.list());
		
		return "artikel/form";
	}
	
	@PostMapping("/form")
	public String prosesForm(Model m, @Valid @ModelAttribute Artikel a,
			BindingResult result, HttpSession session, 
			@RequestParam("file-artikel") MultipartFile[] files) {
		
		if (result.hasErrors()) { 
			m.addAttribute("daftarRubrik", rs.list());
			return "artikel/form";
		}			
		
		String lokasiUpload = tujuanUpload(session).getAbsolutePath();
		String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
	
		as.save(a);
		
		Files f = new Files();
		
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			f.setNamaFile(tanggal + "-" + fileName);
			f.setLinkFile(FOLDER_TUJUAN + "/" + 
					tanggal + "-" + fileName);
			f.setArtikel(a);
			
			File tujuan = new File(lokasiUpload + "/" +
					tanggal + "-" + fileName);
			try {
				file.transferTo(tujuan);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			fs.save(f);
		}				
		
		return "redirect:/artikel/";
	}
	
	@GetMapping("/edit")
	public String formEditArtikel(@RequestParam(value = "id", required = true) String id,
			Model m) {
		
		m.addAttribute("artikel", as.getArtikel(id));
		m.addAttribute("daftarRubrik", rs.list());
		m.addAttribute("files", fs.getFilesByIdArtikel(id));
		
		return "artikel/edit";
	}
	
	@GetMapping("/hapus")
	public String hapusArtikel(@RequestParam(value = "id", required = true) String id) {
		as.delete(id);
		return "redirect:/artikel/";
	}

	private File tujuanUpload(HttpSession session) {
		String lokasiFullPath = session.getServletContext().
				getRealPath(FOLDER_TUJUAN);
		LOGGER.debug("Lokasi Full Path : [{}]", lokasiFullPath);
		
		File hasil = new File(lokasiFullPath);
		if (!hasil.exists()) {
			LOGGER.debug("Lokasi file belum ada, kita buat baru saja");
			hasil.mkdirs();
		}
		return hasil;
	}
	
	@GetMapping("/file/hapus")
	public String hapusFile(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "idartikel", required = true) String idArtikel) {
		fs.delete(id);
		return "redirect:/artikel/edit?id=" + idArtikel;
	}
	
}
