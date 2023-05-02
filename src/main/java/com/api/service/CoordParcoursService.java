package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.Coordonnees;
import com.api.repository.CoordParcoursRepository;

import lombok.Data;

@Data
@Service
public class CoordParcoursService {
	
	@Autowired
	private CoordParcoursRepository coordParcoursRepository;
	
	public Optional<List<Coordonnees>> getCoordonneesByParcours(final Long id) {
		return coordParcoursRepository.findByParcoursId(id);
	}
	
	public Iterable<Coordonnees> getCoordonnees() {
		return coordParcoursRepository.findAll();
	}
	
	@Transactional
	public void deleteCoordonnees(final Long parcoursId) {
		coordParcoursRepository.deleteByParcoursId(parcoursId);
	}
	
	public List<Coordonnees> saveCoordonnees(List<Coordonnees> coordonnees) {
		List<Coordonnees> savedCoords = (List<Coordonnees>) coordParcoursRepository.saveAll(coordonnees);
		return savedCoords;
	}

}
