package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categorie_Produit {
	@Id
	private String codeCatPro;
	private String nom_catPro;
	public Categorie_Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie_Produit(String codeCatPro, String nom_catPro) {
		super();
		this.codeCatPro = codeCatPro;
		this.nom_catPro = nom_catPro;
	}
	public String getCodeCatPro() {
		return codeCatPro;
	}
	public void setCodeCatPro(String codeCatPro) {
		this.codeCatPro = codeCatPro;
	}
	public String getNom_catPro() {
		return nom_catPro;
	}
	public void setNom_catPro(String nom_catPro) {
		this.nom_catPro = nom_catPro;
	}
	

}
