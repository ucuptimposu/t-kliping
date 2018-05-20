package com.timposu.tkliping.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timposu.tkliping.dao.FilesDao;
import com.timposu.tkliping.model.Files;
import com.timposu.tkliping.service.FilesService;

@Service
@Transactional
public class FilesServiceImpl implements FilesService {
	
	@Autowired
	private FilesDao fd;

	@Override
	public void save(Files files) {
		fd.save(files);
	}
	
	@Override
	public void update(Files files) {
		fd.update(files);
	}

	@Override
	public List<Files> list() {
		return fd.list();
	}

	@Override
	public Files getFiles(String id) {
		return fd.getFiles(id);
	}

	@Override
	public void delete(String id) {
		fd.delete(id);
	}
	
	@Override
	public List<Files> getFilesByIdArtikel(String idArtikel) {
		return fd.getFilesByIdArtikel(idArtikel);
	}

}
