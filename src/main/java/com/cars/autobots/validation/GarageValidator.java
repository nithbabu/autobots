package com.cars.autobots.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cars.autobots.command.Vehicle;
import com.google.common.base.Strings;

@Component
public class GarageValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Vehicle.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Vehicle vehicle = (Vehicle) target;
		
		if (Strings.isNullOrEmpty(vehicle.getModelname())) {
			errors.reject("modelname", "Model name cannot be empty");
		}
		if (Strings.isNullOrEmpty(vehicle.getColor())) {
			errors.reject("color", "color cannot be empty");
		}
		if (Strings.isNullOrEmpty(vehicle.getVehicletype())) {
			errors.reject("vehicletype", "Vehicle type cannot be empty");
		}
		if (!(vehicle.getNumofdoors() >= 0)) {
			errors.reject("numberofdoors", "No.of doors cannot be empty");
		}
		if (!(vehicle.getNumofwheels() > 0)) {
			errors.reject("numofwheels", "No.of wheels cannot be empty");
		}
		if (!(vehicle.getCarryingcapacity() >= 0)) {
			errors.reject("carryingcapacity", "Carrying capacity cannot be empty");
		}
		if (!(vehicle.getEnginesize() > 0)) {
			errors.reject("modelname", "Engine size cannot be empty");
		}
		if (!(vehicle.getMaxleanangle() >= 0)) {
			errors.reject("leanangle", "Lean angle cannot be empty");
		}
		
	}
	
	

}
