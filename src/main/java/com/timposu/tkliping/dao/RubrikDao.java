package com.timposu.tkliping.dao;

import java.util.List;

import com.timposu.tkliping.model.Rubrik;

public interface RubrikDao {

	public void save(Rubrik rubrik);
	
	public List<Rubrik> list();
	
	public Rubrik getRubrik(Integer id);
	
	public void delete(Integer id);
	
}
