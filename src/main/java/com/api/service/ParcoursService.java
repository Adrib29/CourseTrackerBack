package com.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Etape;
import com.api.model.Parcours;
import com.api.repository.ParcoursRepository;

import lombok.Data;

@Data
@Service
public class ParcoursService {
	
	@Autowired
	private ParcoursRepository parcoursRepository;
	
	@Autowired
	private EtapeService etapeService;
	
	@Autowired
	private CoordParcoursService coordParcoursService;
	
	public Optional<Parcours> getParcours(final Long id) {
		return parcoursRepository.findById(id);
	}
	
	public Iterable<Parcours> getParcours() {
		return parcoursRepository.findAll();
	}
	
	public void deleteParcours(final Long id) {
		//Récupération des étapes du parcours
		Iterable<Etape> etapes = etapeService.getEtapesByParcoursId(id);
		//Supression des étapes du parcours
		for(Etape e : etapes ) {
			etapeService.deleteEtape(e.getId());
			System.out.println();
		}
		//Supression des coordonnées du parcours
		coordParcoursService.deleteCoordonnees(id);
		
		parcoursRepository.deleteById(id);
	}
	
	public Parcours saveParcours(Parcours parcours) {
		Parcours savedParcours = parcoursRepository.save(parcours);
		return savedParcours;
	}
	
	

}
