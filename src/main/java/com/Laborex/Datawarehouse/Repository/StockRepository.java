package com.Laborex.Datawarehouse.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Stock;
import com.Laborex.Datawarehouse.Model.TypeStock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
	@Query("""
		    SELECT s.quantiteStocke
		    FROM Stock s
		    WHERE EXTRACT(MONTH FROM s.temps.temps) = :mois
		      AND EXTRACT(YEAR FROM s.temps.temps) = :annee
		      AND s.produit.codeProduit = :codeProduit
		      AND s.typeStock = :type
		""")
		Integer findQuantiteStock(@Param("codeProduit") String codeProduit,
		                          @Param("mois") int mois,
		                          @Param("annee") int annee,
		                          @Param("type") TypeStock type);
	//récupérer les enregistrements de type FINAL ou JOURNALIER, associés à chaque jour du mois pour un produit donné.
	@Query("SELECT s FROM Stock s " +
		       "WHERE s.produit.codeProduit = :codeProduit " +
		       "AND FUNCTION('MONTH', s.temps.date) = :mois " +
		       "AND FUNCTION('YEAR', s.temps.date) = :annee " +
		       "AND (s.typeStock = :type1 OR s.typeStock = :type2)")
		List<Stock> findStocksParProduitEtMoisEtType(
		    @Param("codeProduit") String codeProduit,
		    @Param("mois") int mois,
		    @Param("annee") int annee,
		    @Param("type1") TypeStock type1,
		    @Param("type2") TypeStock type2
		);


}
