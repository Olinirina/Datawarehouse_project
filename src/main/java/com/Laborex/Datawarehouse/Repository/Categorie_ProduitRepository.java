package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Categorie_Produit;

@Repository
public interface Categorie_ProduitRepository extends JpaRepository<Categorie_Produit, String>{

}
