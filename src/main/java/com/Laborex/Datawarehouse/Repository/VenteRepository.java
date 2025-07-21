package com.Laborex.Datawarehouse.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, String> {

	@Query("""
		    SELECT SUM(v.quantiteVendu)
		    FROM Vente v
		    WHERE EXTRACT(MONTH FROM v.temps.temps) = :mois
		      AND EXTRACT(YEAR FROM v.temps.temps) = :annee
		      AND v.produit.codeProduit = :codeProduit
		""")
		Double quantiteVendueParProduitEtMois(@Param("codeProduit") String codeProduit,
		                                      @Param("mois") int mois,
		                                      @Param("annee") int annee);

}
