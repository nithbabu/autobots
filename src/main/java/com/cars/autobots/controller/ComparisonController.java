package com.cars.autobots.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cars.autobots.command.ComparisonCommand;
import com.cars.autobots.command.Vehicle;
import com.cars.autobots.service.ComparisonService;
import com.cars.autobots.service.VehicleService;

@Controller
public class ComparisonController {
	
	private static final String COMPARE = "/compare-vehicles";
	private static final String COMPARE_INDEX = "compare-vehicles/index";
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ComparisonService comparisonService;
	
	@GetMapping(COMPARE)
	public String getCompareIndex(ModelMap model) {
		
		ComparisonCommand command = new ComparisonCommand();
		model.addAttribute("viewTable", false);
		
		modelSetter(model, command);
		
		return COMPARE_INDEX;		
	}
	
	@PostMapping(COMPARE)
	public String compare(ModelMap model, ComparisonCommand command, RedirectAttributes attr) {
		List<Vehicle> vehicles = comparisonService.getVehicles(command.getModelnames());
		Map<String, Integer> map = new LinkedHashMap<>();
		Vehicle vehicle = new Vehicle();
		
		if (command.getProperty().equals("No. of doors")) {
			vehicles.forEach(v -> {
				map.put(v.getModelname(), v.getNumofdoors());
			});
		} else if (command.getProperty().equals("No. of wheels")) {
			vehicles.forEach(v -> {
				map.put(v.getModelname(), v.getNumofwheels());
			});
		} else if (command.getProperty().equals("Size of engine")) {
			vehicles.forEach(v -> {
				map.put(v.getModelname(), v.getEnginesize());
			});

		} else if (command.getProperty().equals("Carrying capacity")) {
			vehicles.forEach(v -> {
				map.put(v.getModelname(), v.getCarryingcapacity());
			});

		} else if (command.getProperty().equals("Lean angle")) {
			vehicles.forEach(v -> {
				map.put(v.getModelname(), v.getMaxleanangle());
			});		
		}
		
		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		sortedMap = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		                LinkedHashMap::new));
		model.addAttribute("vehicles", sortedMap);
		model.addAttribute("viewTable", true);
		model.addAttribute("property", command.getProperty());
		modelSetter(model, command);
		return COMPARE_INDEX;
	}
	
	private void modelSetter(ModelMap model, ComparisonCommand command) {
		List<Vehicle> list = vehicleService.listAll();
		List<String> vehicleList = list.stream().map(vehicle -> vehicle.getModelname()).collect(Collectors.toList());
		
		model.addAttribute("vehiclelist", vehicleList);
		model.addAttribute("comparisonCommand", command);
	}

}
