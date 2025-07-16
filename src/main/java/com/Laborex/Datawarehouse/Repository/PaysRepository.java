package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, String> {

}
