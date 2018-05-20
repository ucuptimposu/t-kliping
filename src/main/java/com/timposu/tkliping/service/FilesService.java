package com.timposu.tkliping.service;

import java.util.List;

import com.timposu.tkliping.model.Files;

public interface FilesService {
	
	public void save(Files files);
	
	public void update(Files files);
	
	public List<Files> list();
	
	public Files getFiles(String id);
	
	public void delete(String id);
	
	public List<Files> getFilesByIdArtikel(String idArtikel);

}
