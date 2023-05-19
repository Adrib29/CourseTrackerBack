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
	 * Read - Récupération d'une liste de coordonnées selon l'id d'une étape
	 * 
	 * @param id l'id de l'étape
	 * @return une liste de coordonnées
	 */

	public List<CoordonneesEtape> getCoordonnnesByEtapeId(final Long id) {
		Optional<List<CoordonneesEtape>> coordonnees = coordEtapeService.getCoordonneesByEtape(id);
		if (coordonnees.isPresent()) {
			return coordonnees.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Récupérer toutes les coordonnées
	 * 
	 * @return - un Iterable de coordonnées
	 */
	public Iterable<CoordonneesEtape> getCoordonnees() {
		return coordEtapeService.getCoordonnees();
	}

	/**
	 * Delete - Supprimer les coordonnees d'une étape
	 * 
	 * @param id - l'id de l'étape des coordonnées à supprimer
	 */
	public void deleteCoordonnees(final Long id) {
		coordEtapeService.deleteCoordonnees(id);
	}
}
