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
	 * Create - Creer une nouvelle étape
	 * 
	 * @param l'id du parcours auquelle on ajoute l'étape, l'étape à ajouter
	 * @return L'étape ajoutée
	 */
	@PostMapping("/parcours/{parcoursId}/etape")
	public Etape createEtape(@PathVariable("parcoursId") final Long id, @RequestBody Etape etape) {

		Optional<Parcours> p = parcoursService.getParcours(id);
		if (p.isPresent()) {
			etape.setParcours(p.get());
			etape.setDuree(etapeService.calculDuree(etape.getStartDate(), etape.getEndDate()));
			return etapeService.saveEtape(etape);
		}

		return null;
	}

	/**
	 * Read - Récupérer une étape
	 * 
	 * @param l'id de l'étape à récupérer
	 * @return une étape
	 */
	@GetMapping("/etape/{etapeId}")
	public Etape getEtape(@PathVariable("etapeId") final Long etapeId) {
		Optional<Etape> etape = etapeService.getEtape(etapeId);

		if (etape.isPresent()) {
			return etape.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Récupération de toutes les étapes d'un parcours
	 * 
	 * @return - un itérable d'étapes lié a un parcours (id)
	 */
	@GetMapping("/parcours/{id}/etape")
	public Iterable<Etape> getEtapebyParcoursId(@PathVariable("id") final Long id) {
		Optional<Parcours> parcours = parcoursService.getParcours(id);

		if (parcours.isPresent()) {
			return etapeService.getEtapesByParcoursId(id);
		} else {
			return null;
		}
	}

	/**
	 * Update - Mise a jour d'une étape existante
	 * 
	 * @param id - l'id de l'étape et du parcours auquel il est lié
	 * @return retourne l'étape mise à jour
	 */
	@PutMapping("parcours/{parcoursId}/etape/{etapeId}")
	public Etape updateEtape(@PathVariable("etapeId") final Long etapeId, @RequestBody Etape etape) {

		Optional<Etape> e = etapeService.getEtape(etapeId);

		if (e.isPresent()) {
			Etape currentEtape = e.get();

			List<CoordonneesEtape> actualList = currentEtape.getCoordonneesList();
			List<CoordonneesEtape> newList = etape.getCoordonneesList();

			// Mise à jour des coordonnées si la nouvelle list est non nulle et différente
			// de la list actuelle
			if (newList != null) {
				if (!etapeService.listsAreEquals(actualList, newList)) {
					for (CoordonneesEtape c : newList) {
						c.setEtape(currentEtape);
					}
					// Supression des coordonnées de l'étape
					coordEtapeService.deleteCoordonnees(etapeId);
					coordEtapeService.saveCoordonnees(newList);
				}
			}

			currentEtape.setDistance(etape.getDistance());
			currentEtape.setStartDate(etape.getStartDate());
			currentEtape.setEndDate(etape.getEndDate());
			currentEtape.setDuree(etapeService.calculDuree(currentEtape.getStartDate(), currentEtape.getEndDate()));
			currentEtape.setVitesse(etapeService.calculVitesse(currentEtape.getDuree(), currentEtape.getDistance()));
			etapeService.saveEtape(currentEtape);

			return currentEtape;

		} else {
			return null;
		}
	}

	/**
	 * Delete - Supression d'une étape
	 * 
	 * @param id - l'id de l'étape à supprimer
	 */
	@DeleteMapping("/etape/{id}")
	public void deleteParcours(@PathVariable("id") final Long id) {

		etapeService.deleteEtape(id);
	}

}