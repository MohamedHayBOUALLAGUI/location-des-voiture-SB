package com.tekup.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.location.entities.Modele;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeleRepository extends JpaRepository<Modele, Long> {

}
