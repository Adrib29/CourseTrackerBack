package com.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.Coordonnees;

@Repository
public interface CoordParcoursRepository extends CrudRepository<Coordonnees, Long>{

	@Query("SELECT c FROM Coordonnees c WHERE c.parcours.parcoursId = :parcoursId")
	Optional<List<Coordonnees>> findByParcoursId(@Param("parcoursId") Long parcoursId);
	
	@Modifying
	@Query("DELETE FROM Coordonnees c WHERE c.parcours.parcoursId = :parcoursId")
	void deleteByParcoursId(@Param("parcoursId") Long parcoursId);
}