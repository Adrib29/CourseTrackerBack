package com.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parcours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long parcoursId;
	private String nom;
	private Double distance; // (en m)

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Coordonnees> coordonneesList = new ArrayList<>();

	// Getters et setters
	public Long getId() {
		return parcoursId;
	}

	public void setId(Long id) {
		this.parcoursId = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Coordonnees> getCoordonneesList() {
		return coordonneesList;
	}

	public void setCoordonneesList(List<Coordonnees> c) {
		this.coordonneesList = c;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
}