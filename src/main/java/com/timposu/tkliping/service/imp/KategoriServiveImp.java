package com.timposu.tkliping.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timposu.tkliping.dao.KategoriDao;
import com.timposu.tkliping.model.Kategori;
import com.timposu.tkliping.service.KategoriService;

@Service
@Transactional
public class KategoriServiveImp implements KategoriService {
	
	@Autowired
	private KategoriDao kd;

	@Override
	public void save(Kategori kategori) {
		kd.save(kategori);
	}

	@Override
	public List<Kategori> list() {
		return kd.list();
	}

	@Override
	public Kategori getKategori(Integer id) {
		return kd.getKategori(id);
	}

	@Override
	public void delete(Integer id) {
		kd.delete(id);
	}

}
