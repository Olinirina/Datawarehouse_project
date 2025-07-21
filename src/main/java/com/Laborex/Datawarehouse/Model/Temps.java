package com.Laborex.Datawarehouse.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Temps {
	@Id
	private String code_temps;
	@Column(name = "temps")
	private Date temps;
	private int jour;
	private int mois;
	private int annee;
	private int trimestre;
	public Temps() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public Temps(String code_temps, Date temps, int jour, int mois, int annee, int trimestre) {
		super();
		this.code_temps = code_temps;
		this.temps = temps;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		this.trimestre = trimestre;
	}

	public String getCode_temps() {
		return code_temps;
	}
	public void setCode_temps(String code_temps) {
		this.code_temps = code_temps;
	}
	public Date getTemps() {
		return temps;
	}
	public void setTemps(Date temps) {
		this.temps = temps;
	}
	

}
