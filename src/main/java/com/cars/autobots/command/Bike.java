package com.cars.autobots.command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
public class Bike extends Vehicle{
	
	private int maxLeanAngle;

	public int getMaxLeanAngle() {
		return maxLeanAngle;
	}

	public void setMaxLeanAngle(int maxLeanAngle) {
		this.maxLeanAngle = maxLeanAngle;
	}

}
