package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plant {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String commonName;
  private String scientificName;
  private String typeOfPlant;

  protected Plant() {}

  public Plant(String commonName, String scientificName, String typeOfPlant) {
	  this.commonName = commonName;
	  this.scientificName = scientificName;
	  this.typeOfPlant = typeOfPlant;
  }

  @Override
  public String toString() {
    return String.format(
        "Plant[id=%d, commonName='%s', scientificName='%s', typeOfPlant='%s']",
        id, commonName, scientificName, typeOfPlant);
  }

  public Long getId() {
    return id;
  }

  public String getCommonName() {
    return commonName;
  }

  public String getScientificName() {
    return scientificName;
  }
  
  public String getTypeOfPlant() {
	  return typeOfPlant;
  }
}