package com.timposu.tkliping.dao;

import java.util.List;

import com.timposu.tkliping.model.Files;

public interface FilesDao {
	
	public void save(Files files);
	
	public void update(Files files);
	
	public List<Files> list();
	
	public Files getFiles(String id);
	
	public void delete(String id);

}
