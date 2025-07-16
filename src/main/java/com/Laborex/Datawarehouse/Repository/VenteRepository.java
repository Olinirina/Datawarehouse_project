package com.Laborex.Datawarehouse.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, String> {

    @Query("SELECT SUM(v.quantiteVendu) FROM Vente v " +
           "WHERE v.produit.codeProduit = :codeProduit " +
           "AND v.temps.temps BETWEEN :debut AND :fin")
    Integer sumQuantiteByProduitAndDateRange(@Param("codeProduit") String codeProduit,
                                              @Param("debut") Date debut,
                                              @Param("fin") Date fin);
}
