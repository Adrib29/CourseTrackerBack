package com.api.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.model.Coordonnees;
import com.api.service.CoordParcoursService;


public class CoordParcoursControler {
	@Autowired
	private CoordParcoursService coordparcoursService;
	
	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */

	public List<Coordonnees> createParours(List<Coordonnees> coordonnees) {
		return coordparcoursService.saveCoordonnees(coordonnees);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */

	public List<Coordonnees> getParcours(final Long id) {
		Optional<List<Coordonnees>> coordonnees = coordparcoursService.getCoordonneesByParcours(id);
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
	public Iterable<Coordonnees> getParcours() {
		return coordparcoursService.getCoordonnees();
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	public void deleteCoordonnees( final Long id) {
		coordparcoursService.deleteCoordonnees(id);
	}
}
