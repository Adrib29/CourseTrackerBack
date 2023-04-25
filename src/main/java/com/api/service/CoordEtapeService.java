package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.CoordonneesEtape;
import com.api.repository.CoordEtapeRepository;

import lombok.Data;

@Data
@Service
public class CoordEtapeService {
	
	@Autowired
	private CoordEtapeRepository coordEtapeRepository;
	
	public Optional<List<CoordonneesEtape>> getCoordonneesByEtape(final Long id) {
		return coordEtapeRepository.findByEtapeId(id);
	}
	
	public Iterable<CoordonneesEtape> getCoordonnees() {
		return coordEtapeRepository.findAll();
	}
	
	@Transactional
	public void deleteCoordonnees(final Long parcoursId) {
		coordEtapeRepository.deleteByEtapeId(parcoursId);
	}
	
	public List<CoordonneesEtape> saveCoordonnees(List<CoordonneesEtape> coordonnees) {
		List<CoordonneesEtape> savedCoords = (List<CoordonneesEtape>) coordEtapeRepository.saveAll(coordonnees);
		return savedCoords;
	}

}
