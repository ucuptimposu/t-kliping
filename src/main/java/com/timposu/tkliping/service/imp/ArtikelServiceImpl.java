package com.timposu.tkliping.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timposu.tkliping.dao.ArtikelDao;
import com.timposu.tkliping.model.Artikel;
import com.timposu.tkliping.service.ArtikelService;

@Service
@Transactional
public class ArtikelServiceImpl implements ArtikelService {

	@Autowired
	private ArtikelDao ad;
	
	@Override
	public void save(Artikel artikel) {
		ad.save(artikel);
	}
	
	@Override
	public void update(Artikel artikel) {
		ad.update(artikel);
	}

	@Override
	public List<Artikel> list() {
		return ad.list();
	}

	@Override
	public Artikel getArtikel(String id) {
		return ad.getArtikel(id);
	}

	@Override
	public void delete(String id) {
		ad.delete(id);
	}

}
