package com.api.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.Etape;
import com.api.repository.EtapeRepository;
import com.api.model.CoordonneesEtape;

import lombok.Data;


@Data
@Service
public class EtapeService {
	
	@Autowired
	private EtapeRepository etapeRepository;
	
	@Autowired
	private CoordEtapeService coordEtapeService;
	
	public Optional<Etape> getEtape(final Long id) {
		return etapeRepository.findById(id);
	}
	
	public Iterable<Etape> getEtapesByParcoursId(final Long parcoursId) {
		return etapeRepository.findByParcoursId(parcoursId);
	}
	
	@Transactional	
	public void deleteEtape(final Long id) {
		//Suppression des coordonées de l'étape
		coordEtapeService.deleteCoordonnees(id);
		
		etapeRepository.deleteByEtapeId(id);
	}
	
	public Etape saveEtape(Etape etape) {
		Etape savedEtape = etapeRepository.save(etape);
		return savedEtape;
	}
	
	public Long calculDuree(LocalDateTime startDate, LocalDateTime endDate) {
		
		return Duration.between(startDate, endDate).toMinutes();

	}
	
	public Double calculVitesse(Long duree, Double distance) {

		Double hours = duree/60.0;
		if(hours > 0.0) {
			return Math.round((distance / hours) * 100.0) / 100.0;
		}
		
		return 0.0;
		
	}
	
	

	public boolean listsAreEquals(List<CoordonneesEtape> list1, List<CoordonneesEtape> list2) {
	    if (list1.size() != list2.size()) {
	        return false;
	    }
	    for (int i = 0; i < list1.size(); i++) {
	        if(!(coordAreEquals(list1.get(i),list2.get(i))))
	            return false;
	    }
	    
	    return true;
	}
	
	public boolean coordAreEquals(CoordonneesEtape coord1, CoordonneesEtape coord2) {
		 if (coord1 == null || coord2 == null) {
		        return false;
		    }

		    double epsilon = 0.00001;
		    boolean latEquals = Math.abs(coord1.getLatitude() - coord2.getLatitude()) < epsilon;
		    boolean lngEquals = Math.abs(coord1.getLongitude() - coord2.getLongitude()) < epsilon;
		    return latEquals && lngEquals;
	}
	
}
