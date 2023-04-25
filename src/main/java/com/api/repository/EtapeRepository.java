package com.api.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.Etape;

@Repository
public interface EtapeRepository extends CrudRepository<Etape, Long>{

	@Query("SELECT e FROM Etape e WHERE e.parcours.parcoursId = :parcoursId")
	Iterable<Etape> findByParcoursId(@Param("parcoursId") Long parcoursId);
	
	@Modifying
	@Query("DELETE FROM Etape e WHERE e.parcours.parcoursId = :parcoursId")
	void deleteByEtapeId(@Param("parcoursId") Long parcoursId);
}