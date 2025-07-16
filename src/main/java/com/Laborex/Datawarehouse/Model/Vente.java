package com.Laborex.Datawarehouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vente {
	 @Id
	    private String codeVente;

	 //chaque ligne de vente ne concerne qu’un seul produit, client et temps
	    @ManyToOne
	    @JoinColumn(name = "codeProduit") 
	    private Produit produit;

	    @ManyToOne
	    @JoinColumn(name = "code_client") 
	    private Client client;

	    @ManyToOne
	    @JoinColumn(name = "code_temps") 
	    private Temps temps;

	    private int quantiteVendu;
	    private double montantVendu;
	    private double margeBrute;
	    
		public Vente() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Vente(String codeVente, Produit produit, Client client, Temps temps, int quantiteVendu,
				double montantVendu, double margeBrute) {
			super();
			this.codeVente = codeVente;
			this.produit = produit;
			this.client = client;
			this.temps = temps;
			this.quantiteVendu = quantiteVendu;
			this.montantVendu = montantVendu;
			this.margeBrute = margeBrute;
			
		}
		public String getCodeVente() {
			return codeVente;
		}
		public void setCodeVente(String codeVente) {
			this.codeVente = codeVente;
		}
		public Produit getProduit() {
			return produit;
		}
		public void setProduit(Produit produit) {
			this.produit = produit;
		}
		public Client getClient() {
			return client;
		}
		public void setClient(Client client) {
			this.client = client;
		}
		public Temps getTemps() {
			return temps;
		}
		public void setTemps(Temps temps) {
			this.temps = temps;
		}
		public int getQuantiteVendu() {
			return quantiteVendu;
		}
		public void setQuantiteVendu(int quantiteVendu) {
			this.quantiteVendu = quantiteVendu;
		}
		public double getMontantVendu() {
			return montantVendu;
		}
		public void setMontantVendu(double montantVendu) {
			this.montantVendu = montantVendu;
		}
		public double getMargeBrute() {
			return margeBrute;
		}
		public void setMargeBrute(double margeBrute) {
			this.margeBrute = margeBrute;
		}
		
	    

}
