package com.timposu.tkliping.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timposu.tkliping.dao.FilesDao;
import com.timposu.tkliping.model.Artikel;
import com.timposu.tkliping.model.Files;

@Repository
public class FilesDaoImpl implements FilesDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void save(Files files) {
		session.getCurrentSession().save(files);		
	}
	
	@Override
	public void update(Files files) {
		session.getCurrentSession().update(files);		
	}

	@Override
	public List<Files> list() {
		@SuppressWarnings({ "unchecked", "deprecation", "unused" })
		List<Files> list = session.getCurrentSession()
				.createCriteria(Files.class).list();
		return list;
	}

	@Override
	public Files getFiles(String id) {
		Files files = session.getCurrentSession().
				get(Files.class, id);
		return files;
	}

	@Override
	public void delete(String id) {
		Files files = session.getCurrentSession().
				get(Files.class, id);
		session.getCurrentSession().delete(files);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Files> getFilesByIdArtikel(String idArtikel) {
		Artikel artikel = session.getCurrentSession().
				get(Artikel.class, idArtikel);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.getCurrentSession()
				.createCriteria(Files.class)
				.add(Restrictions.le("artikel", artikel));
		return criteria.list();
	}

}
