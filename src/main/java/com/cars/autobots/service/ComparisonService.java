package com.cars.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.repo.VehicleRepository;

public interface ComparisonService {

	List<Vehicle> getVehicles(List<String> modelnames);

	@Service
	class Default implements ComparisonService {

		@Autowired
		private VehicleRepository vehicleRepository;

		@Override
		public List<Vehicle> getVehicles(List<String> modelnames) {
			return vehicleRepository.findAllById(modelnames);
		}

	}
	
}
