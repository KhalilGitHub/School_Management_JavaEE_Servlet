package com.objis.cameroun.ges.domain;

public class Personne {

	// Les instances de la classe
		protected String nom;
		protected String prenom;
		protected String genre;
		protected String adresse;
		
		public Personne() {
			super();
		}

		public Personne(String nom, String prenom, String genre, String adresse) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.genre = genre;
			this.adresse = adresse;
		}


		public String getNom() {
			return nom;
		}


		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getPrenom() {
			return prenom;
		}


		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


		public String getGenre() {
			return genre;
		}


		public void setGenre(String genre) {
			this.genre = genre;
		}


		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}	
}
