package com.timposu.tkliping.service;

import java.util.List;

import com.timposu.tkliping.model.Kategori;

public interface KategoriService {
	public void save(Kategori kategori);
	
	public List<Kategori> list();
	
	public Kategori getKategori(Integer id);
	
	public void delete(Integer id);
}
