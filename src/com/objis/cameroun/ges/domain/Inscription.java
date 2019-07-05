package com.objis.cameroun.ges.domain;

import java.math.BigDecimal;
import java.sql.Blob;

import java.util.Date;

public class Inscription {
	
	private String matricule;
	private Eleve eleve;
    private BigDecimal frais;
    private Date date;
    private Blob image;
    private String  photo;
    
    public Inscription() {
		super();
	}

	public Inscription(String matricule, Eleve eleve, BigDecimal frais, Date date) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
	}

	public Inscription(String matricule, Eleve eleve, BigDecimal frais, Date date, String photo) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
		this.photo = photo;
	}

	public Inscription(String matricule, Eleve eleve, BigDecimal frais, Date date, Blob image) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
		this.image = image;
	}

	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public BigDecimal getFrais() {
		return frais;
	}

	public void setFrais(BigDecimal frais) {
		this.frais = frais;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}


	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Inscription [matricule=" + matricule + ", eleve=" + eleve + ", frais=" + frais + ", date=" + date
				+ ", image=" + image + ", photo=" + photo + "]";
	}
	
	

	
}
