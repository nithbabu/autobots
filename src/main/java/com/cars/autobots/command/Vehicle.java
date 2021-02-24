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
    int numofdoors;
	
	@Column(name = "carryingcapacity")
    int carryingcapacity;
	
	@Column(name = "maxleanangle")
    int maxleanangle;
    
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
	public int getNumofdoors() {
		return numofdoors;
	}
	public void setNumofdoors(int numofdoors) {
		this.numofdoors = numofdoors;
	}
	public int getCarryingcapacity() {
		return carryingcapacity;
	}
	public void setCarryingcapacity(int carryingcapacity) {
		this.carryingcapacity = carryingcapacity;
	}
	public int getMaxleanangle() {
		return maxleanangle;
	}
	public void setMaxleanangle(int maxleanangle) {
		this.maxleanangle = maxleanangle;
	}
    

}
