package com.cars.autobots.controller;

import com.cars.autobots.command.ComparisonCommand;
import com.cars.autobots.command.Vehicle;
import com.cars.autobots.service.ComparisonService;
import com.cars.autobots.service.VehicleService;
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
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import org.mockito.junit.MockitoJUnit;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.*;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ComparisonControllerTest {

    @Mock
    private VehicleService vehicleService;

    @Mock
    private ComparisonService comparisonService;

    @InjectMocks
    private ComparisonController comparisonController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);

    private Vehicle vehicle1 = new Vehicle();
    private Vehicle vehicle2 = new Vehicle();
    private List<Vehicle> vehicleList = new ArrayList<>();

    private ComparisonCommand command = new ComparisonCommand();

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

        List<String> modelNames = new ArrayList<>();
        modelNames.add(vehicle1.getModelname());
        modelNames.add(vehicle2.getModelname());

        command.setModelnames(modelNames);
        command.setProperty("Size of engine");
    }

    @Test
    public void getCompareIndexTest() {

        ModelMap model = new ModelMap();

        model.addAttribute("viewTable", false);
        model.addAttribute("comparisonCommand", command);

        modelSetter(model, command);

        comparisonController.getCompareIndex(model);
    }

    @Test
    public void compare() {
        ModelMap model = new ModelMap();

        RedirectAttributes attr = mock(RedirectAttributes.class);

        when(comparisonService.getVehicles(command.getModelnames())).thenReturn(vehicleList);

        List<Vehicle> vehicleListNew = comparisonService.getVehicles(command.getModelnames());

        assertEquals(vehicleListNew, vehicleList);

        comparisonController.compare(model, command, attr);
    }

    private void modelSetter(ModelMap model, ComparisonCommand command) {
        when(vehicleService.listAll()).thenReturn(vehicleList);

//        List<String> vehicles = vehicleList.stream().map(vehicle -> vehicle.getModelname()).collect(Collectors.toList());
    }

}
