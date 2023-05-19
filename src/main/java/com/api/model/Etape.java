package com.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Etape {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long etapeId;
	private Double distance; // (en km)

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private Long duree; // (en min)
	private Double vitesse; // (en km/h)

	@OneToMany(mappedBy = "etape", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CoordonneesEtape> coordonneesList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parcoursId")
	private Parcours parcours;

	public Long getId() {
		return this.etapeId;

	}

	public void setId(Long id) {
		this.etapeId = id;
	}

	public List<CoordonneesEtape> getCoordonneesList() {
		return coordonneesList;
	}

	public void setCoordonneesList(List<CoordonneesEtape> c) {
		this.coordonneesList = c;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Long getDuree() {
		return duree;
	}

	public void setDuree(Long duree) {
		this.duree = duree;
	}

	public Double getVitesse() {
		return vitesse;
	}

	public void setVitesse(Double vitesse) {
		this.vitesse = vitesse;
	}

}
