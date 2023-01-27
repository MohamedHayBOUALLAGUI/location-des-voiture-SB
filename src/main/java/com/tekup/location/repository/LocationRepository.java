package com.tekup.location.repository;


import com.tekup.location.entities.Location;
import com.tekup.location.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l, User u where l.user.id = :id")
    List<Location> findAllByUserId(@Param("id") Long id);

    @Query("select l from Location l, Voiture v where l.voiture.id = :id")
    List<Location> findAllByVoitureId(@Param("id") Long id);

}
