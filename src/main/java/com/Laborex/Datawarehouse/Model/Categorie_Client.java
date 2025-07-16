package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Categorie_Client {
	@Id
	private String code_catCli;
	private String nom_catCli;
	@ManyToOne
	@JoinColumn(name="code_pays")
	private Pays localisation;
	public Categorie_Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie_Client(String code_catCli, String nom_catCli, Pays localisation) {
		super();
		this.code_catCli = code_catCli;
		this.nom_catCli = nom_catCli;
		this.localisation = localisation;
	}
	public String getCode_catCli() {
		return code_catCli;
	}
	public void setCode_catCli(String code_catCli) {
		this.code_catCli = code_catCli;
	}
	public String getNom_catCli() {
		return nom_catCli;
	}
	public void setNom_catCli(String nom_catCli) {
		this.nom_catCli = nom_catCli;
	}
	public Pays getLocalisation() {
		return localisation;
	}
	public void setLocalisation(Pays localisation) {
		this.localisation = localisation;
	}
	

}
