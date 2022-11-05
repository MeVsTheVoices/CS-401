package com.cs401.hw4.hw4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plant {

  private Long id;
  
  private String commonName;
  private String scientificName;
  private String typeOfPlant;
  private int minimumGrowingZone;
  private int maximumGrowingZone;

  //empty construct needs to be here to ensure compatibility
  protected Plant() {}

  public Plant(String commonName, String scientificName, String typeOfPlant, int minimumGrowingZone, int maximumGrowingZone) {
	  this.commonName = commonName;
	  this.scientificName = scientificName;
	  this.typeOfPlant = typeOfPlant;
	  this.minimumGrowingZone = minimumGrowingZone;
	  this.maximumGrowingZone = maximumGrowingZone;
  }
  
  //remaining member functions are kept from the demo and renamed

  @Override
  public String toString() {
    return String.format(
        "Plant[id=%d, commonName='%s', scientificName='%s', typeOfPlant='%s', minimumGrowingZone='%d', maximumGrowingZone='%d']",
        id, commonName, scientificName, typeOfPlant, minimumGrowingZone, maximumGrowingZone);
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
  
  public int getMinimumGrowingZone() {
	  return minimumGrowingZone;
  }
  
  public int getMaximumGrowingZone() {
	  return maximumGrowingZone;
  }
}