package com.api.model;

import java.util.ArrayList;
import java.util.Date;
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
    private Double distance; //(en km)
	
    private Date startDate;
    private Date endDate;
    
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
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
