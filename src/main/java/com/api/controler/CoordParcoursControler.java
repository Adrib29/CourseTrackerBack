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
	 * Create - Ajouter une liste de coordonnées
	 * @param la liste de coordonnées à sauvegarder
	 * @return la liste de coordonnées sauvegardée
	 */

	public List<Coordonnees> createParours(List<Coordonnees> coordonnees) {
		return coordparcoursService.saveCoordonnees(coordonnees);
	}
	
	/**
	 * Read - Récupération de toutes les coordonées
	 * @return - Un itérable de coordonnées
	 */
	public Iterable<Coordonnees> getCoordonnees() {
		return coordparcoursService.getCoordonnees();
	}
	
	
	/**
	 * Delete - Supression des coordonées d'un parcours
	 * @param id - L'id du parcours où supprimer les coordonnées
	 */
	public void deleteCoordonnees( final Long id) {
		coordparcoursService.deleteCoordonnees(id);
	}
}
