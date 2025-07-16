package com.Laborex.Datawarehouse.Model;

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
