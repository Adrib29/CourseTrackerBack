package com.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.CoordonneesEtape;

@Repository
public interface CoordEtapeRepository extends CrudRepository<CoordonneesEtape, Long>{

	@Query("SELECT c FROM CoordonneesEtape c WHERE c.etape.etapeId = :etapeId")
	Optional<List<CoordonneesEtape>> findByEtapeId(@Param("etapeId") Long etapeId);
	
	@Modifying
	@Query("DELETE FROM CoordonneesEtape c WHERE c.etape.etapeId = :etapeId")
	void deleteByEtapeId(@Param("etapeId") Long etapeId);
}