package com.cars.autobots.service;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.repo.VehicleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository repo;

    @InjectMocks
    private VehicleService.Default vehicleService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);

    private Vehicle vehicle1 = new Vehicle();
    private Vehicle vehicle2 = new Vehicle();
    private List<Vehicle> vehicleList =  new ArrayList<>();

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
    public void listAllTest() {
        when(repo.findAll()).thenReturn(vehicleList);

    }

    @Test
    public void saveTest() {
        vehicleService.save(vehicle1);
        verify(repo, times(1)).save(vehicle1);
    }

    @Test
    public void getTest() {
        String modelName = vehicle1.getModelname();
        Optional<Vehicle> vehicle = Optional.ofNullable(vehicle1);
        when(repo.findById(modelName)).thenReturn(vehicle);
    }

    @Test
    public void deleteTest() {
        String modelName = vehicle1.getModelname();
        vehicleService.delete(modelName);
        verify(repo, times(1)).deleteById(modelName);
    }

    @Test
    public void updateTest() {
        String modelname = vehicle1.getModelname();
        Optional<Vehicle> vehicle = Optional.ofNullable(vehicle1);
        when(repo.findById(modelname)).thenReturn(vehicle);
        Vehicle vehicle3 = vehicleService.get(modelname);
        vehicleService.update(modelname, vehicle3);
        verify(repo, times(1)).saveAndFlush(vehicle3);
    }



}
