package com.tekup.location.repository;

import com.tekup.location.entities.Role;
import com.tekup.location.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.location.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	
	  // JPQL query
	  @Query(value = "select u from User u where u.username = :username")
	  //Optional<Utilisateur> findUtilisateurByEmail(@Param("username") String username);	  
	  User findByUsername(@Param("username")String username);

	@Query("select u from User u where u.role = :r")
	List<User> findAllByRole(@Param("r") Role role);

	@Query("select u from User u, Role r where u.role.id = :id and u.role.id = r.id")
	List<User> findAllByRoleId(@Param("id") Long id);
	  
}







