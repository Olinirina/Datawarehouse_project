package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Fournisseur {
	@Id
	private String code_fournisseur;
	private String nom_Fournisseur;
	@ManyToOne
	@JoinColumn(name="ode_pays")
	private Pays pays;
	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fournisseur(String code_fournisseur, String nom_Fournisseur, Pays pays) {
		super();
		this.code_fournisseur = code_fournisseur;
		this.nom_Fournisseur = nom_Fournisseur;
		this.pays = pays;
	}
	public String getCode_fournisseur() {
		return code_fournisseur;
	}
	public void setCode_fournisseur(String code_fournisseur) {
		this.code_fournisseur = code_fournisseur;
	}
	public String getNom_Fournisseur() {
		return nom_Fournisseur;
	}
	public void setNom_Fournisseur(String nom_Fournisseur) {
		this.nom_Fournisseur = nom_Fournisseur;
	}
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	

}
