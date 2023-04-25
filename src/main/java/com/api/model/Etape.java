package com.api.model;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long etapeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parcoursId")
    private Parcours parcours;
    
    @OneToMany(mappedBy = "etape", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordonneesEtape> coordonneesList = new ArrayList<>();
    
    public Long getId() {
    	return etapeId;
        
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

}
