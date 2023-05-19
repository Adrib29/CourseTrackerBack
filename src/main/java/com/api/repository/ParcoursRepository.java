package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Parcours;

@Repository
public interface ParcoursRepository extends CrudRepository<Parcours, Long>{

	
}