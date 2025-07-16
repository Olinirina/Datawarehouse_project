package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, String> {

}
