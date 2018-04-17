package com.timposu.tkliping.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
//	@Autowired
//	private FilesService fs;
	
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
	public String formArtikel(@RequestParam(value = "id", 
			required = false) String id, Model m) {
		
		m.addAttribute("artikel", new Artikel());
		m.addAttribute("daftarRubrik", rs.list());
		
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
			BindingResult result, HttpSession session, 
			@RequestParam("foto") MultipartFile files) {
		
		if (result.hasErrors()) { 
			m.addAttribute("daftarRubrik", rs.list());
			return "artikel/form";
		}			
		System.out.println("tes");
		String lokasiUpload = tujuanUpload(session).getAbsolutePath();
		
		String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
		
		a.setFiles(FOLDER_TUJUAN + File.separator + 
					tanggal + "-" + files.getOriginalFilename());
		
		File tujuan = new File(lokasiUpload + File.separator +
				tanggal + "-" + files.getOriginalFilename());
		
		try {
			files.transferTo(tujuan);;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}		
		
		if (a.getId() == null || a.getId().isEmpty()) {
			as.save(a);
		} else {
			as.update(a);
		}
		
//		Files f = new Files();
		
//		for (CommonsMultipartFile file : files) {
//			String fileName = file.getName();
//			f.setNamaFile(FOLDER_TUJUAN + File.separator + 
//					tanggal + "-" + fileName);
//			f.setArtikel(a);
//			
//			File tujuan = new File(lokasiUpload + File.separator +
//					tanggal + "-" + fileName);
//			try {
//				file.transferTo(tujuan);
//			} catch (IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}			
//			fs.save(f);
//		}
		
//		a.setFile(FOLDER_TUJUAN + File.separator + 
//				tanggal + "-" +files.getOriginalFilename());
//		a.setFile2(folderTujuan + File.separator + 
//				tanggal + "-" +hasilUpload2.getOriginalFilename());
//		
		
			
//		if (listFiles != null && listFiles.size() > 0) {
//			for (MultipartFile multipartFile : listFiles) {
//				String fileName = files.getOriginalFilename();
//				filesNames.add(fileName);
//				
//				File tujuan = new File(lokasiUpload + File.separator +
//						tanggal + "-" + files.getOriginalFilename());
//				try {
//					files.transferTo(tujuan);
////					hasilUpload2.transferTo(tujuan2);
//					if (a.getId() == null || a.getId().isEmpty()) {
//						as.save(a);
//					} else {
//						as.update(a);
//					}
//				} catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
				
		
		
//		File tujuan1 = new File(lokasiUpload + File.separator +
//				tanggal + "-" + hasilUpload.getOriginalFilename());
//		File tujuan2 = new File(lokasiUpload + File.separator +
//				tanggal + "-" + hasilUpload2.getOriginalFilename());
	
		
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
	
}
