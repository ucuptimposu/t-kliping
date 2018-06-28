package com.timposu.tkliping.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timposu.tkliping.dao.ArtikelDao;
import com.timposu.tkliping.model.Artikel;

@Repository
public class ArtikelDaoImpl implements ArtikelDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void save(Artikel artikel) {
		session.getCurrentSession().save(artikel);
	}
	
	@Override
	public void update(Artikel artikel) {
		session.getCurrentSession().update(artikel);
	}

	@Override
	public List<Artikel> list() {
		@SuppressWarnings({ "unchecked", "deprecation", "unused"  })
		List<Artikel> list = session.getCurrentSession()
				.createCriteria(Artikel.class).list();
		return list;
	}

	@Override
	public Artikel getArtikel(String id) {
		Artikel artikel = session.getCurrentSession()
				.get(Artikel.class, id);
		return artikel;
	}

	@Override
	public void delete(String id) {
		Artikel artikel = session.getCurrentSession()
				.get(Artikel.class, id);
		session.getCurrentSession().delete(artikel);
	}
	
	@Override
	public List<Artikel> listLimit(Integer pageNo) {
		@SuppressWarnings("rawtypes")
		Query query = session.getCurrentSession().createQuery("from Artikel");
		Integer page = (pageNo - 1) * 5;
		query.setFirstResult(page);
		query.setMaxResults(5);
		@SuppressWarnings("unchecked")
		List<Artikel> list = query.list();
		return list;
	}

}
