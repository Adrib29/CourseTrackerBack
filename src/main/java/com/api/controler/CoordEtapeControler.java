package com.api.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.model.CoordonneesEtape;
import com.api.service.CoordEtapeService;

public class CoordEtapeControler {
	@Autowired
	private CoordEtapeService coordEtapeService;
	
	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */

	public List<CoordonneesEtape> createParours(List<CoordonneesEtape> coordonnees) {
		return coordEtapeService.saveCoordonnees(coordonnees);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */

	public List<CoordonneesEtape> getEtape(final Long id) {
		Optional<List<CoordonneesEtape>> coordonnees = coordEtapeService.getCoordonneesByEtape(id);
		if(coordonnees.isPresent()) {
			return coordonnees.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	public Iterable<CoordonneesEtape> getParcours() {
		return coordEtapeService.getCoordonnees();
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	public void deleteCoordonnees(final Long id) {
		
		coordEtapeService.deleteCoordonnees(id);
	}
}
