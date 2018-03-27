package com.timposu.tkliping.property;

import java.beans.PropertyEditorSupport;

import com.timposu.tkliping.model.Artikel;
import com.timposu.tkliping.service.ArtikelService;

public class ArtikelProperty extends PropertyEditorSupport {
	
	private ArtikelService as;
	
	public ArtikelProperty(ArtikelService as) {
		this.as = as;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Artikel a = as.getArtikel(text);
		setValue(a);
	}

}
