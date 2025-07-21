package com.Laborex.Datawarehouse.Model;

import java.time.LocalDate;
import java.util.Date;

//Pour Transmettre les données vers le frontend
public class RotationStockDTO {
		private String codeProduit;
	    private String nomProduit;
	    private double quantiteVendue;
	    private double stockMoyen;
	    private double rotation;
	    private String interpretation;
	    private String interpretationVelocite;
	    private String interpretationCouverture;
		public String getCodeProduit() {
			return codeProduit;
		}
		public void setCodeProduit(String codeProduit) {
			this.codeProduit = codeProduit;
		}
		public String getNomProduit() {
			return nomProduit;
		}
		public void setNomProduit(String nomProduit) {
			this.nomProduit = nomProduit;
		}
		public double getQuantiteVendue() {
			return quantiteVendue;
		}
		public void setQuantiteVendue(double quantiteVendue) {
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
		public String getInterpretation() {
			return interpretation;
		}
		public void setInterpretation(String interpretation) {
			this.interpretation = interpretation;
		}
		public RotationStockDTO(String codeProduit, String nomProduit, double quantiteVendue, double stockMoyen,
				double rotation, String interpretation,String interpretationVelocite,String interpretationCouverture) {
			super();
			this.codeProduit = codeProduit;
			this.nomProduit = nomProduit;
			this.quantiteVendue = quantiteVendue;
			this.stockMoyen = stockMoyen;
			this.rotation = rotation;
			this.interpretation = interpretation;
			this.interpretationVelocite=interpretationVelocite;
			this.interpretationCouverture= interpretationCouverture;
		}
		public String getInterpretationCouverture() {
			return interpretationCouverture;
		}
		public void setInterpretationCouverture(String interpretationCouverture) {
			this.interpretationCouverture = interpretationCouverture;
		}
		public String getInterpretationVelocite() {
			return interpretationVelocite;
		}
		public void setInterpretationVelocite(String interpretationVelocite) {
			this.interpretationVelocite = interpretationVelocite;
		}
		public RotationStockDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	


    
}