package com.cars.autobots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.service.VehicleService;

@Controller
public class GarageController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/garage")
	public String getForm(Model model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "/createVehicle/form";
	}
	
	@PostMapping("/garage")
	public String postForm(Vehicle vehicle) {
		System.out.println("post endpoint reached");
		if(vehicle.getVehicletype().equals("bike")) {
			vehicle.setNumofdoors("0");
			vehicle.setCarryingcapacity("0");
		} else if(vehicle.getVehicletype().equals("car")) {
			vehicle.setMaxleanangle("0");
			vehicle.setCarryingcapacity("0");
		} else if(vehicle.getVehicletype().equals("lorry")) {
			vehicle.setMaxleanangle("0");
		}
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@GetMapping("/vehicles")
	public String getIndex(ModelMap model) {
		List<Vehicle> list = vehicleService.listAll();
		model.addAttribute("vehicles", list);
		model.addAttribute("isAdmin", true);
		return "vehicleIndex";
	}
	
	@GetMapping("/garage/editVehicle")
	public String editVehicle(ModelMap model, @RequestParam(name = "modelname") String modelname) {
		Vehicle vehicle = vehicleService.get(modelname);
		model.addAttribute("isEdit", true);
		model.addAttribute("vehicle", vehicle);
		return "/createVehicle/form";
	}
	
	@PostMapping("/garage/editVehicle")
	public String updateVehicle(ModelMap model, @RequestParam(name = "modelname") String modelname, Vehicle vehicle) {
		vehicleService.update(modelname.split(",")[0], vehicle);
		return "redirect:/vehicles";
	}
	
	@GetMapping("/garage/deleteVehicle")
	public String deleteVehicle(ModelMap model, @RequestParam(name = "modelname") String modelname) {
		vehicleService.delete(modelname);
		return "redirect:/vehicles";
	}

}
