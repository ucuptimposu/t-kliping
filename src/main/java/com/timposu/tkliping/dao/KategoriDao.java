package com.timposu.tkliping.dao;

import java.util.List;

import com.timposu.tkliping.model.Kategori;

public interface KategoriDao {

	public void save(Kategori kategori);
	
	public List<Kategori> list();
	
	public Kategori getKategori(Integer id);
	
	public void delete(Integer id);
	
}
