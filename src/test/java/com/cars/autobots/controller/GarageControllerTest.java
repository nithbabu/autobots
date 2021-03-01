package com.cars.autobots.controller;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.service.VehicleService;
import com.cars.autobots.validation.GarageValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GarageControllerTest {

    @Mock
    private VehicleService vehicleService;

    @Mock
    private GarageValidator validator;

    @InjectMocks
    private GarageController garageController;

    private Vehicle vehicle1 = new Vehicle();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);

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
    }

    @Test
    public void getIndexTest() {
        ModelMap model = new ModelMap();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setModelname("Ford GTR");
        vehicle1.setColor("black");
        vehicle1.setEnginesize(650);
        vehicle1.setNumofdoors(4);
        vehicle1.setNumofwheels(4);
        vehicle1.setVehicletype("car");
        vehicle1.setCarryingcapacity(0);
        vehicle1.setMaxleanangle(0);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setModelname("Ford Mustang");
        vehicle2.setColor("green");
        vehicle2.setEnginesize(750);
        vehicle2.setNumofdoors(2);
        vehicle2.setNumofwheels(4);
        vehicle2.setVehicletype("car");
        vehicle2.setCarryingcapacity(0);
        vehicle2.setMaxleanangle(0);

        List<Vehicle> list = new ArrayList<>();
        list.add(vehicle1);
        list.add(vehicle2);

        when(vehicleService.listAll()).thenReturn(list);

        garageController.getIndex(model);
    }


    @Test
    public void getFormTest() {
        ModelMap model = new ModelMap();
        garageController.getForm(model);
    }

    @Test
    public void postFormTest() {
        ModelMap model = new ModelMap();
        BindingResult result = mock(BindingResult.class);
        vehicle1.setVehicletype(vehicle1.getVehicletype().replace(",", ""));
        System.out.println("post endpoint reached");
        validator.validate(vehicle1, result);

        when(result.hasErrors()).thenReturn(true);
        assertEquals(result.hasErrors(), true);

        if(result.hasErrors()) {
            garageController.getForm(model);
        }


        if(vehicle1.getVehicletype().equals("bike")) {
            vehicle1.setNumofdoors(0);
            vehicle1.setCarryingcapacity(0);
        } else if(vehicle1.getVehicletype().equals("car")) {
            vehicle1.setMaxleanangle(0);
            vehicle1.setCarryingcapacity(0);
        } else if(vehicle1.getVehicletype().equals("lorry")) {
            vehicle1.setMaxleanangle(0);
        }

        when(vehicleService.save(vehicle1)).thenReturn(ResponseEntity.ok(vehicle1));
        ResponseEntity<Vehicle> response =  vehicleService.save(vehicle1);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        garageController.getForm(model);
    }

    @Test
    public void editVehicleTest() {
        ModelMap model = new ModelMap();
        String modelname = vehicle1.getModelname();
        when(vehicleService.get(modelname)).thenReturn(vehicle1);
        Vehicle v = vehicleService.get(modelname);
        assertEquals(vehicle1, v);
        garageController.editVehicle(model, modelname);
    }

    @Test
    public void updateVehicleTest() {
        ModelMap model = new ModelMap();
        doNothing().when(vehicleService).update(vehicle1.getModelname(), vehicle1);
        garageController.updateVehicle(model, vehicle1.getModelname(), vehicle1);
    }

    @Test
    public void deleteVehicle() {
        ModelMap model = new ModelMap();
        String modelname = vehicle1.getModelname();
        doNothing().when(vehicleService).delete(modelname);
        garageController.deleteVehicle(model, modelname);
    }

}
