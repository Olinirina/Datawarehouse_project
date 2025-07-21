package com.Laborex.Datawarehouse.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Produit {
	@Id
    private String codeProduit;

    private String nomProduit;
    private double prixAchat;
    private double prixVente;
    private int unite;
    //un produit n'a qu'un seul fournisseur
    @ManyToOne
    @JoinColumn(name = "code_fournisseur") // clé étrangère
    private Fournisseur fournisseur;
    //plusieurs produits peuvent appartenir à une même catégorie
    @ManyToOne
    @JoinColumn(name = "codeCatPro") // clé étrangère
    private Categorie_Produit categorie;
    
    private int seuilStockMinimal;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produit(String codeProduit, String nomProduit, double prixAchat, double prixVente, int unite,
			Fournisseur fournisseur, Categorie_Produit categorie, int seuilStockMinimal) {
		super();
		this.codeProduit = codeProduit;
		this.nomProduit = nomProduit;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.unite = unite;
		this.fournisseur = fournisseur;
		this.categorie = categorie;
		this.seuilStockMinimal=seuilStockMinimal;
	}
	public String getCodeProduit() {
		return codeProduit;
	}
	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}
	public int getSeuilStockMinimal() {
		return seuilStockMinimal;
	}
	public void setSeuilStockMinimal(int seuilStockMinimal) {
		this.seuilStockMinimal = seuilStockMinimal;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public double getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
	public double getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}
	public int getUnite() {
		return unite;
	}
	public void setUnite(int unite) {
		this.unite = unite;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public Categorie_Produit getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie_Produit categorie) {
		this.categorie = categorie;
	}
    

}
