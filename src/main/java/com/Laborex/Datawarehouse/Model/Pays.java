package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pays {
	@Id
	private String code_pays;
	private String nom_pays;
	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pays(String code_pays, String nom_pays) {
		super();
		this.code_pays = code_pays;
		this.nom_pays = nom_pays;
	}
	public String getCode_pays() {
		return code_pays;
	}
	public void setCode_pays(String code_pays) {
		this.code_pays = code_pays;
	}
	public String getNom_pays() {
		return nom_pays;
	}
	public void setNom_pays(String nom_pays) {
		this.nom_pays = nom_pays;
	}
	
	

}
