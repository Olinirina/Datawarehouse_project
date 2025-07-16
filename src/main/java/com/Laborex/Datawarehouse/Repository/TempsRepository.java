package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Temps;

@Repository
public interface TempsRepository extends JpaRepository<Temps, String> {

}
