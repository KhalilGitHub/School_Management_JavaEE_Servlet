package com.objis.cameroun.ges.domain;


public class Eleve extends Personne{

	// Les instances de la classe
	private int age;
	private String classe;

	
	public Eleve() {
		super();
	}

	/* Les constructeurs */	
	public Eleve(String nom, String prenom, String genre, String adresse, int age, String classe) {
		super(nom, prenom, genre, adresse);
		this.age = age;
		this.classe = classe;
	}

	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		return "Nom=" + nom + ", Prenom=" + prenom + ", Genre=" + genre + ", Adresse=" + adresse + ", Naissance=" + age + ", Classe=" + classe ;
	}
			
}
