package com.cars.autobots.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cars.autobots.command.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
