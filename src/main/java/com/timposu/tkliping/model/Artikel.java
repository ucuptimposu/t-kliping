package com.timposu.tkliping.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "artikel")
public class Artikel {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	
	@NotNull
	@NotEmpty
	@Size(min = 3)
	@Column(nullable = false)
	private String judul;
	
	@Column(nullable = true)
	private String namaPenulis;
	
	@NotNull
	@NotEmpty
	@Size(min = 3)
	@Column(nullable = false)
	private String namaMedia;
	
	@Column(nullable = true)
	private String edisi;
	
	@NotNull
	@NotEmpty
	@Column(nullable = false)
	private String halaman;
	
	@Temporal(TemporalType.DATE)
	private Date tanggal;
	
	@Column(nullable = true)
	private String tags;
	
	@ManyToOne
	@JoinColumn(name = "id_rubrik")
	private Rubrik rubrik;
	
	@Column(nullable = true)
	private String file1;
	
	@Column(nullable = true)
	private String file2;
	
	@Column(nullable = true)
	private String ket;

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public Rubrik getRubrik() {
		return rubrik;
	}

	public void setRubrik(Rubrik rubrik) {
		this.rubrik = rubrik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getNamaPenulis() {
		return namaPenulis;
	}

	public void setNamaPenulis(String namaPenulis) {
		this.namaPenulis = namaPenulis;
	}

	public String getNamaMedia() {
		return namaMedia;
	}

	public void setNamaMedia(String namaMedia) {
		this.namaMedia = namaMedia;
	}

	public String getEdisi() {
		return edisi;
	}

	public void setEdisi(String edisi) {
		this.edisi = edisi;
	}

	public String getHalaman() {
		return halaman;
	}

	public void setHalaman(String halaman) {
		this.halaman = halaman;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}		
	
}