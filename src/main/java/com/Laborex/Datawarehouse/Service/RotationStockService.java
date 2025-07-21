package com.Laborex.Datawarehouse.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Laborex.Datawarehouse.Model.Produit;
import com.Laborex.Datawarehouse.Model.RotationStockDTO;
import com.Laborex.Datawarehouse.Model.Stock;
import com.Laborex.Datawarehouse.Model.TypeStock;
import com.Laborex.Datawarehouse.Repository.ProduitRepository;
import com.Laborex.Datawarehouse.Repository.StockRepository;
import com.Laborex.Datawarehouse.Repository.VenteRepository;

@Service
public class RotationStockService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public List<RotationStockDTO> calculerRotationParMoisPourTousLesProduits(int mois, int annee) {
        List<Produit> produits = produitRepository.findAll();
        List<RotationStockDTO> resultats = new ArrayList<>();
        
        //Nombre total de jour dans ce mois
        int jourDansLeMois= YearMonth.of(annee,mois).lengthOfMonth();
        

        for (Produit pr : produits) {
            Double quantiteVendue = venteRepository.quantiteVendueParProduitEtMois(pr.getCodeProduit(), mois, annee);
            if (quantiteVendue == null) quantiteVendue = 0.0;

            Integer stockInitial = stockRepository.findQuantiteStock(pr.getCodeProduit(), mois, annee, TypeStock.INITIAL);
            Integer stockFinal = stockRepository.findQuantiteStock(pr.getCodeProduit(), mois, annee, TypeStock.FINAL);
            Integer stockActuel = stockRepository.findQuantiteStock(pr.getCodeProduit(), mois, annee, TypeStock.REAPPRO_INTER);

            if (stockInitial == null || stockFinal == null) {
                // Ne pas ajouter le produit s’il manque une info cruciale
                continue;
            }

            double stockMoyen = (stockInitial + stockFinal) / 2.0;
            double rotation = (stockMoyen == 0) ? 0 : quantiteVendue / stockMoyen;
            
            //Calcul de la velocité
            double velocite=quantiteVendue/jourDansLeMois;
            String interpretationVelocite;
            if(velocite > 10) {
            	interpretationVelocite="Produit à forte demande";
            }else if(velocite < 3) {
            	interpretationVelocite="Demande faible,éviter le surstock";
            }else {
            	interpretationVelocite="Demande stable";
            }

            //Rotation
            String interpretation;
            if (rotation < 1) {
                interpretation = "Produit qui stagne, à surveiller";
            } else if (rotation >= 2 && rotation <= 4) {
                interpretation = "Produit sain, bonne performance";
            } else if (rotation > 5) {
                interpretation = "Produit qui part vite, risque de rupture";
            } else {
                interpretation = "Rotation moyenne, surveiller";
            }
            
            //Couverture de stock
            double couvertureStock= stockActuel/velocite;
            String interpretationCouverture;
            if(couvertureStock < 5) {
            	interpretationCouverture ="Stock faible, risque de rupture  ";
            } else if(couvertureStock >5 || couvertureStock <15) {
            	interpretationCouverture="Bon equilibre";
            }else if(couvertureStock > 30) {
            	interpretationCouverture= "Surstock possible";
            }else {
            	interpretationCouverture="";
            }

            RotationStockDTO dto = new RotationStockDTO();
            dto.setCodeProduit(pr.getCodeProduit());
            dto.setNomProduit(pr.getNomProduit());
            dto.setQuantiteVendue(quantiteVendue);
            dto.setStockMoyen(stockMoyen);
            dto.setRotation(rotation);
            dto.setInterpretation(interpretation);
            dto.setInterpretationVelocite(interpretationVelocite);
            dto.setInterpretationCouverture(interpretationCouverture);

            resultats.add(dto);
        }

        return resultats;
    }
    
    //Calcul du taux de rupture
    public List<RotationStockDTO> calculerTauxRuptureParProduit(int mois,int annee){
    	List<Produit> produits = produitRepository.findAll();
        List<RotationStockDTO> resultats = new ArrayList<>();
        
        //Generer les dates de debut et de fin
        LocalDate debut= LocalDate.of(annee, mois, 1);
        LocalDate fin= debut.withDayOfMonth(debut.lengthOfMonth());
        for (Produit pr : produits) {
        	//Recuperer tous les stocks JOURNALIER ou FINAL du mois
            List<Stock> stocks= stockRepository.findStocksParProduitEtMoisEtType(pr.getCodeProduit(), mois, annee, TypeStock.FINAL, TypeStock.REAPPRO_INTER);
            
            //COnstruire le map jour-> quantite
            Map<LocalDate, Integer> stockParJour= new HashMap<>();
            for (Stock s : stocks) {
            	//LocalDate date=s.getTemps().getTemps();
            	//nstockParJour.put(date, s.getQuantiteStocke());            }
        
        }
            return resultats;
    }
    
}
