package com.cars.autobots.service;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.repo.VehicleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ComparisonServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private ComparisonService.Default service;

    private Vehicle vehicle1 = new Vehicle();
    private Vehicle vehicle2 = new Vehicle();
    private List<Vehicle> vehicleList = new ArrayList<>();

    @Before
    public void init() {
        vehicle1.setModelname("Ford GTR");
        vehicle1.setColor("black");
        vehicle1.setEnginesize(650);
        vehicle1.setNumofdoors(4);
        vehicle1.setNumofwheels(4);
        vehicle1.setVehicletype("car");
        vehicle1.setCarryingcapacity(0);
        vehicle1.setMaxleanangle(0);

        vehicle2.setModelname("Yamaha R15");
        vehicle2.setColor("blue");
        vehicle2.setEnginesize(200);
        vehicle2.setNumofdoors(0);
        vehicle2.setNumofwheels(2);
        vehicle2.setVehicletype("bike");
        vehicle2.setCarryingcapacity(0);
        vehicle2.setMaxleanangle(45);

        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

    }


    @Test
    public void getVehiclesTest() {
        List<String> modelnames = new ArrayList<>();
        modelnames.add(vehicle1.getModelname());
        modelnames.add(vehicle2.getModelname());
        when(vehicleRepository.findAllById(modelnames)).thenReturn(vehicleList);
        List<Vehicle> vehicles = service.getVehicles(modelnames);
        assertEquals(vehicles, vehicleList);
    }

}
