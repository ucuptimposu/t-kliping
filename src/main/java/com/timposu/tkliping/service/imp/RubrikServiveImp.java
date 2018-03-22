package com.timposu.tkliping.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timposu.tkliping.dao.RubrikDao;
import com.timposu.tkliping.model.Rubrik;
import com.timposu.tkliping.service.RubrikService;

@Service
@Transactional
public class RubrikServiveImp implements RubrikService {
	
	@Autowired
	private RubrikDao rd;

	@Override
	public void save(Rubrik rubrik) {
		rd.save(rubrik);
	}

	@Override
	public List<Rubrik> list() {
		return rd.list();
	}

	@Override
	public Rubrik getRubrik(Integer id) {
		return rd.getRubrik(id);
	}

	@Override
	public void delete(Integer id) {
		rd.delete(id);
	}

}
