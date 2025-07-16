package com.Laborex.Datawarehouse.Model;

import java.time.LocalDate;
import java.util.Date;

//Pour Transmettre les données vers le frontend
public class RotationStockDto {
    private String codeProduit;
    private Date debut;
    private Date fin;
    private int quantiteVendue;
    private double stockMoyen;
    private double rotation;
	public RotationStockDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RotationStockDto(String codeProduit, Date debut, Date fin, int quantiteVendue, double stockMoyen,
			double rotation) {
		super();
		this.codeProduit = codeProduit;
		this.debut = debut;
		this.fin = fin;
		this.quantiteVendue = quantiteVendue;
		this.stockMoyen = stockMoyen;
		this.rotation = rotation;
	}
	public String getCodeProduit() {
		return codeProduit;
	}
	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public int getQuantiteVendue() {
		return quantiteVendue;
	}
	public void setQuantiteVendue(int quantiteVendue) {
		this.quantiteVendue = quantiteVendue;
	}
	public double getStockMoyen() {
		return stockMoyen;
	}
	public void setStockMoyen(double stockMoyen) {
		this.stockMoyen = stockMoyen;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

    // constructeur + getters + setters


    
}