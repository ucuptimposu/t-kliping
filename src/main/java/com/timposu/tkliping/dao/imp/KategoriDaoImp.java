package com.timposu.tkliping.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timposu.tkliping.dao.KategoriDao;
import com.timposu.tkliping.model.Kategori;

@Repository
public class KategoriDaoImp implements KategoriDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void save(Kategori kategori) {
		session.getCurrentSession().saveOrUpdate(kategori);
	}

	@Override
	public List<Kategori> list() {
		@SuppressWarnings({ "unchecked", "deprecation", "unused" })
		List<Kategori> list = session.getCurrentSession()
				.createCriteria(Kategori.class).list();
		return list;
	}
	
	@Override
	public Kategori getKategori(Integer id) {
		Kategori kategori = session.getCurrentSession().
				get(Kategori.class, id);
		return kategori;
	}

	@Override
	public void delete(Integer id) {
		Kategori kategori = session.getCurrentSession().
				get(Kategori.class, id);
		session.getCurrentSession().delete(kategori);
	}

}
