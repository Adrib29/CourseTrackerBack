package com.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.Etape;
import com.api.repository.EtapeRepository;

import lombok.Data;

@Data
@Service
public class EtapeService {
	
	@Autowired
	private EtapeRepository etapeRepository;
	
	public Optional<Etape> getEtape(final Long id) {
		return etapeRepository.findById(id);
	}
	
	public Iterable<Etape> getEtapesByParcoursId(final Long parcoursId) {
		return etapeRepository.findByParcoursId(parcoursId);
	}
	
	@Transactional	
	public void deleteEtape(final Long id) {
		etapeRepository.deleteByEtapeId(id);
	}
	
	public Etape saveEtape(Etape etape) {
		Etape savedEtape = etapeRepository.save(etape);
		return savedEtape;
	}
	
	

}
