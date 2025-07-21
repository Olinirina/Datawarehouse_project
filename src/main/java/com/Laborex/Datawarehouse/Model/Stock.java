package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Stock {

    @Id
    private String codeStock;

    @ManyToOne
    @JoinColumn(name = "code_temps") 
    private Temps temps;

    @ManyToOne
    @JoinColumn(name = "codeProduit") 
    private Produit produit;

   
    private int quantiteStocke;
    
    public TypeStock getTypeStock() {
		return typeStock;
	}

	public void setTypeStock(TypeStock typeStock) {
		this.typeStock = typeStock;
	}

	public Stock(String codeStock, Temps temps, Produit produit, int quantiteStocke, TypeStock typeStock) {
		super();
		this.codeStock = codeStock;
		this.temps = temps;
		this.produit = produit;
		this.quantiteStocke = quantiteStocke;
		this.typeStock = typeStock;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	private TypeStock typeStock;

    // Getters & Setters

    public String getCodeStock() {
        return codeStock;
    }

    public void setCodeStock(String codeStock) {
        this.codeStock = codeStock;
    }

    public Temps getTemps() {
        return temps;
    }

    public void setTemps(Temps temps) {
        this.temps = temps;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantiteStocke() {
        return quantiteStocke;
    }

    public void setQuantiteStocke(int quantiteStocke) {
        this.quantiteStocke = quantiteStocke;
    }
}
