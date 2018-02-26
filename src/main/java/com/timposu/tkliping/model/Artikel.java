package com.timposu.tkliping.model;

import java.util.Date;

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
	@JoinColumn(name = "id_kategori")
	private Kategori kategori;
	
	@Column(nullable = true)
	private String gambar1;
	
	@Column(nullable = true)
	private String gambar2;
	
	@Column(nullable = true)
	private String gambar3;
	
	@Column(nullable = true)
	private String gambar4;

	public String getGambar1() {
		return gambar1;
	}

	public void setGambar1(String gambar1) {
		this.gambar1 = gambar1;
	}

	public String getGambar2() {
		return gambar2;
	}

	public void setGambar2(String gambar2) {
		this.gambar2 = gambar2;
	}

	public String getGambar3() {
		return gambar3;
	}

	public void setGambar3(String gambar3) {
		this.gambar3 = gambar3;
	}

	public String getGambar4() {
		return gambar4;
	}

	public void setGambar4(String gambar4) {
		this.gambar4 = gambar4;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
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