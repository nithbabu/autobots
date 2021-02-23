package com.cars.autobots.command;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle", schema = "vehicleschema")
public class Vehicle {
	
	@Id
    @Column(name = "modelname")
    String modelname;
	
	@Column(name = "color")
	String color;
	
	@Column(name = "enginesize")
	int enginesize;
	
	@Column(name = "numofwheels")
    int numofwheels;
	
	@Column(name = "vehicletype")
    String vehicletype;
	
	@Column(name = "numofdoors")
    String numofdoors;
	
	@Column(name = "carryingcapacity")
    String carryingcapacity;
	
	@Column(name = "maxleanangle")
     String maxleanangle;
    
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getEnginesize() {
		return enginesize;
	}
	public void setEnginesize(int enginesize) {
		this.enginesize = enginesize;
	}
	public int getNumofwheels() {
		return numofwheels;
	}
	public void setNumofwheels(int numofwheels) {
		this.numofwheels = numofwheels;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getNumofdoors() {
		return numofdoors;
	}
	public void setNumofdoors(String numofdoors) {
		this.numofdoors = numofdoors;
	}
	public String getCarryingcapacity() {
		return carryingcapacity;
	}
	public void setCarryingcapacity(String carryingcapacity) {
		this.carryingcapacity = carryingcapacity;
	}
	public String getMaxleanangle() {
		return maxleanangle;
	}
	public void setMaxleanangle(String maxleanangle) {
		this.maxleanangle = maxleanangle;
	}
    

}
