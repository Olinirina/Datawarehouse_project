package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Client {
	@Id
	private String code_client;
	private String nom_client;
	@ManyToOne
	@JoinColumn(name="code_catCli")
	private Categorie_Client categorieCli;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String code_client, String nom_client, Categorie_Client categorieCli) {
		super();
		this.code_client = code_client;
		this.nom_client = nom_client;
		this.categorieCli = categorieCli;
	}
	public String getCode_client() {
		return code_client;
	}
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public Categorie_Client getCategorieCli() {
		return categorieCli;
	}
	public void setCategorieCli(Categorie_Client categorieCli) {
		this.categorieCli = categorieCli;
	}
	

}
