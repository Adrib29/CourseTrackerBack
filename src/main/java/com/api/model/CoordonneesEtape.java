package com.api.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class CoordonneesEtape {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    //private Long id;
    private Double latitude;
    private Double longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapeId")
    private Etape etape;


    /*public Long getId() {
    	return id;
        
    }

    public void setId(Long id) {
        this.id = id;
    }*/
    
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public void setEtape(Etape etape) {
		this.etape = etape;
	}

    
}