package com.timposu.tkliping.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timposu.tkliping.dao.RubrikDao;
import com.timposu.tkliping.model.Rubrik;

@Repository
public class RubrikDaoImp implements RubrikDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void save(Rubrik rubrik) {
		session.getCurrentSession().saveOrUpdate(rubrik);
	}

	@Override
	public List<Rubrik> list() {
		@SuppressWarnings({ "unchecked", "deprecation", "unused" })
		List<Rubrik> list = session.getCurrentSession()
				.createCriteria(Rubrik.class).list();
		return list;
	}
	
	@Override
	public Rubrik getRubrik(Integer id) {
		Rubrik rubrik = session.getCurrentSession().
				get(Rubrik.class, id);
		return rubrik;
	}

	@Override
	public void delete(Integer id) {
		Rubrik rubrik = session.getCurrentSession().
				get(Rubrik.class, id);
		session.getCurrentSession().delete(rubrik);
	}

}
