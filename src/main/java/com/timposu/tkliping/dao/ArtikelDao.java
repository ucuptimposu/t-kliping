package com.timposu.tkliping.dao;

import java.util.List;

import com.timposu.tkliping.model.Artikel;

public interface ArtikelDao {
	public void save(Artikel artikel);
	
	public void update(Artikel artikel);
	
	public List<Artikel> list();
	
	public Artikel getArtikel(String id);
	
	public void delete(String id);
}
