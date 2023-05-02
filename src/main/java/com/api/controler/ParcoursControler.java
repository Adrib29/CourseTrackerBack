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

import com.api.service.CoordParcoursService;
import com.api.service.ParcoursService;
import com.api.model.Coordonnees;
import com.api.model.Parcours;

@RestController
public class ParcoursControler {
	
	@Autowired
	private ParcoursService parcoursService;
	@Autowired
	private CoordParcoursService coordParcoursService;

	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/parcours")
	public Parcours createParours(@RequestBody Parcours parcours) {		
		return parcoursService.saveParcours(parcours);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/parcours/{id}")
	public Parcours getParcours(@PathVariable("id") final Long id) {
		Optional<Parcours> parcours = parcoursService.getParcours(id);

		if(parcours.isPresent()) {
			return parcours.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/parcours")
	public Iterable<Parcours> getParcours() {
		return parcoursService.getParcours();
	}
	
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/parcours/{id}")
	public Parcours updateParcours(@PathVariable("id") final Long id, @RequestBody Parcours parcours) {
		Optional<Parcours> e = parcoursService.getParcours(id);
		if(e.isPresent()) {
			Parcours currentParcours = e.get();
			
			String nom = parcours.getNom();
			if(nom != null) {
				currentParcours.setNom(nom);
			}
			
			List<Coordonnees> list = parcours.getCoordonneesList();
			if(list != null) {
				for(Coordonnees c : list) {
					c.setParcours(currentParcours);
				}
				coordParcoursService.saveCoordonnees(list);
			}	
			
		    Double distance = parcours.getDistance();
			if(distance != null) {
				currentParcours.setDistance(distance);
			}
			
			parcoursService.saveParcours(currentParcours);
			return currentParcours;
			
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/parcours/{id}")
	public void deleteParcours(@PathVariable("id") final Long id) {
		parcoursService.deleteParcours(id);
	}
	

}