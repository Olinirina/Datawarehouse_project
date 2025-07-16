package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, String> {

}
