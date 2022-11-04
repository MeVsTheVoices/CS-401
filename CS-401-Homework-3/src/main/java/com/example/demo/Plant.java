package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plants")
public class Plant {

  //member variables, named specifically to be found by spring
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  //was trying to populate db based on sql statements in a .sql file in the resources
  //might get this to work one day, but, my brain is gravey, and this needs to be turned in
  //the @column annotation simply names the field it would be stored in in the table
  //instead of letting spring come up with these names for itself
  @Column(name="COMMONNAME")
  private String commonName;
  @Column(name="SCIENTIFICNAME")
  private String scientificName;
  @Column(name="TYPEOFPLANT")
  private String typeOfPlant;
  @Column(name="MINIMUMGROWINGZONE")
  private int minimumGrowingZone;
  @Column(name="MAXIMUMGROWINGZONE")
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