package com.timposu.tkliping.service;

import java.util.List;

import com.timposu.tkliping.model.Artikel;

public interface ArtikelService {
	
	public void save(Artikel artikel);
	
	public void update(Artikel artikel);
	
	public List<Artikel> list();
	
	public Artikel getArtikel(String id);
	
	public void delete(String id);

}
