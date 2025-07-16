package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Categorie_Client;

@Repository
public interface Categorie_ClientRepository extends JpaRepository<Categorie_Client, String>{

}
