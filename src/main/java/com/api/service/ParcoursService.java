package com.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.model.Parcours;
import com.api.repository.ParcoursRepository;

import lombok.Data;

@Data
@Service
public class ParcoursService {
	
	@Autowired
	private ParcoursRepository parcoursRepository;
	
	public Optional<Parcours> getParcours(final Long id) {
		return parcoursRepository.findById(id);
	}
	
	public Iterable<Parcours> getParcours() {
		return parcoursRepository.findAll();
	}
	
	public void deleteParcours(final Long id) {
		parcoursRepository.deleteById(id);
	}
	
	public Parcours saveParcours(Parcours parcours) {
		Parcours savedParcours = parcoursRepository.save(parcours);
		return savedParcours;
	}
	
	

}
