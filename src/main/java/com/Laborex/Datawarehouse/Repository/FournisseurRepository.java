package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, String> {

}
