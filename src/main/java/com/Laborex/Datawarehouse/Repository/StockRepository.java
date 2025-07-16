package com.Laborex.Datawarehouse.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    @Query("SELECT AVG(s.quantiteStocke) FROM Stock s " +
           "WHERE s.produit.codeProduit = :codeProduit " +
           "AND s.temps.temps BETWEEN :debut AND :fin")
    Double calculateStockAverage(@Param("codeProduit") String codeProduit,
                                 @Param("debut") Date debut,
                                 @Param("fin") Date fin);
}
