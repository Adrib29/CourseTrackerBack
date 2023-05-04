package com.api.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.CoordEtapeService;
import com.api.service.EtapeService;
import com.api.service.ParcoursService;
import com.api.model.CoordonneesEtape;
import com.api.model.Etape;
import com.api.model.Parcours;

@RestController
public class EtapeControler {
	
	@Autowired
	private EtapeService etapeService;
	@Autowired
	private CoordEtapeService coordEtapeService;
	@Autowired
	private ParcoursService parcoursService;
	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/parcours/{parcoursId}/etape")
	public Etape createEtape(@PathVariable("parcoursId") final Long id, @RequestBody Etape etape) {	
		
		Optional<Parcours> p =  parcoursService.getParcours(id);
		if(p.isPresent()) {
			etape.setParcours(p.get());	
			return etapeService.saveEtape(etape);
		}
		
		return null;
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 * /parcours/{parcoursId}
	 */
	@GetMapping("/etape/{etapeId}")
	public Etape getEtape(@PathVariable("etapeId") final Long etapeId) {
		Optional<Etape> etape = etapeService.getEtape(etapeId);
		
		if(etape.isPresent()) {
			return etape.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/parcours/{id}/etape")
	public Iterable<Etape> getEtapebyParcoursId(@PathVariable("id") final Long id) {
		Optional<Parcours> parcours = parcoursService.getParcours(id);

		if(parcours.isPresent()) {
			return etapeService.getEtapesByParcoursId(id);
		} else {
			return null;
		}
	}
	
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("parcours/{parcoursId}/etape/{etapeId}")
	public Etape updateEtape(@PathVariable("etapeId") final Long etapeId, @RequestBody Etape etape) {
		Optional<Etape> e = etapeService.getEtape(etapeId);
		if(e.isPresent()) {
			Etape currentEtape = e.get();
			
			List<CoordonneesEtape> list = etape.getCoordonneesList();

			if(list != null) {
				//currentParcours.setCoordonneesList(list);
				for(CoordonneesEtape c : list) {
					c.setEtape(currentEtape);
				}
				//Supression des coordonnées de l'étape
				coordEtapeService.deleteCoordonnees(etapeId);
				coordEtapeService.saveCoordonnees(list);
				System.out.println("saved");
				currentEtape.setDistance(etape.getDistance());
			}
			
			currentEtape.setStartDate(etape.getStartDate());
			currentEtape.setEndDate(etape.getEndDate());
			
			etapeService.saveEtape(currentEtape);
			return currentEtape;
			
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/etape/{id}")
	public void deleteParcours(@PathVariable("id") final Long id) {

		etapeService.deleteEtape(id);
	}
	

}