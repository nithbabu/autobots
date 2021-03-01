package com.cars.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.repo.VehicleRepository;

public interface VehicleService {

    List<Vehicle> listAll();

    ResponseEntity<Vehicle> save(Vehicle vehicle);

    Vehicle get(String modelname);

    void delete(String id);

    void update(String modelname, Vehicle vehicle);

    @Service
    @Qualifier("vehicleService")
    class Default implements VehicleService {

        @Autowired
        private VehicleRepository repo;

        @Override
        public List<Vehicle> listAll() {
            return repo.findAll();
        }

        @Override
        public ResponseEntity<Vehicle> save(Vehicle vehicle) {
            repo.save(vehicle);
            return ResponseEntity.ok(vehicle);
        }

        @Override
        public Vehicle get(String modelname) {
            return repo.findById(modelname).get();
        }

        @Override
        public void delete(String id) {
            repo.deleteById(id);
        }

        @Override
        public void update(String modelname, Vehicle vehicle) {
            Vehicle item = repo.findById(modelname.split(",")[0]).get();
            item = vehicle;
            repo.saveAndFlush(vehicle);
        }
    }

}
