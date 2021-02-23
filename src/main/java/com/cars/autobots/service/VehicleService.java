package com.cars.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.repo.VehicleRepository;

@Service
public class VehicleService {
	
    @Autowired
    private VehicleRepository repo;
     
    public List<Vehicle> listAll() {   	
        return repo.findAll();
    }
     
    public void save(Vehicle vehicle) {
    	repo.findAll();
        repo.save(vehicle);
    }
     
    public Vehicle get(String modelname) {
        return repo.findById(modelname).get();
    }
     
    public void delete(String id) {
        repo.deleteById(id);
    }
    
    public void update(String modelname, Vehicle vehicle) {
    	Vehicle item = repo.findById(modelname.split(",")[0]).get();
    	item = vehicle;
    	repo.saveAndFlush(vehicle);
    }

}
