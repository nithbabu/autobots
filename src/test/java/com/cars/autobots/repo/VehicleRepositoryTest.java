//package com.cars.autobots.repo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.junit.runner.Runner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.cars.autobots.command.Vehicle;
//import com.cars.autobots.service.VehicleService;
//
//@SpringBootTest
//public class VehicleRepositoryTest {
//
//	@Autowired
//	private VehicleRepository vehicleRepository;
//
//	@Autowired
//	private VehicleService vehicleService;
//
//	@Test
//    public void listAll() {
//        Vehicle vehicle1 = new Vehicle();
//        vehicle1.setModelname("Ford GTR");
//        vehicle1.setColor("black");
//        vehicle1.setEnginesize(650);
//        vehicle1.setNumofdoors(4);
//        vehicle1.setNumofwheels(4);
//        vehicle1.setVehicletype("car");
//        vehicle1.setCarryingcapacity(0);
//        vehicle1.setMaxleanangle(0);
//
//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setModelname("Ford Mustang");
//        vehicle2.setColor("green");
//        vehicle2.setEnginesize(750);
//        vehicle2.setNumofdoors(2);
//        vehicle2.setNumofwheels(4);
//        vehicle2.setVehicletype("car");
//        vehicle2.setCarryingcapacity(0);
//        vehicle2.setMaxleanangle(0);
//
//        List<Vehicle> list = new ArrayList<>();
//        list.add(vehicle1);
//        list.add(vehicle2);
//
//        when(vehicleRepository.findAll()).thenReturn(list);
//        assertEquals(2, vehicleService.listAll().size());
//    }
//
//}
