package com.timposu.tkliping.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rubrik")
public class Rubrik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(min = 3)
	@Column(nullable = false, unique = true)
	private String namaRubrik;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "rubrik")
	private List<Artikel> listArtikel = new ArrayList<>();
	
	public List<Artikel> getListArtikel() {
		return listArtikel;
	}

	public void setListArtikel(List<Artikel> listArtikel) {
		this.listArtikel = listArtikel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamaRubrik() {
		return namaRubrik;
	}

	public void setNamaRubrik(String namaRubrik) {
		this.namaRubrik = namaRubrik;
	}
	
}
