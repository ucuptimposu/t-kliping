package com.timposu.tkliping.service;

import java.util.List;

import com.timposu.tkliping.model.Rubrik;

public interface RubrikService {
	
	public void save(Rubrik rubrik);
	
	public List<Rubrik> list();
	
	public Rubrik getRubrik(Integer id);
	
	public void delete(Integer id);
}
