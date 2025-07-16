package com.Laborex.Datawarehouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Laborex.Datawarehouse.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

}
