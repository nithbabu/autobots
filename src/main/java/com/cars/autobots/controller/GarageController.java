package com.cars.autobots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cars.autobots.command.Vehicle;
import com.cars.autobots.service.VehicleService;
import com.cars.autobots.validation.GarageValidator;

@Controller
public class GarageController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private GarageValidator validator;
	
	@GetMapping("/garage")
	public String getForm(ModelMap model) {
		if(model.get("commandBindingResult") != null ) {
			model.addAttribute("org.springframework.validation.BindingResult.vehicle", model.get("commandBindingResult"));
            model.addAttribute("errors", model.get("commandBindingResult"));
		} else {		
			Vehicle vehicle = new Vehicle();
			model.addAttribute("vehicle", vehicle);
		}
		model.addAttribute("isEdit", false);
		return "/createVehicle/form";
	}
	
	@PostMapping("/garage")
	public String postForm(Vehicle vehicle, BindingResult result, RedirectAttributes attr) {
		vehicle.setVehicletype(vehicle.getVehicletype().replace(",", ""));
		System.out.println("post endpoint reached");
		validator.validate(vehicle, result);
		
		if(result.hasErrors()) {
            attr.addFlashAttribute("commandBindingResult", result);
            attr.addFlashAttribute("vehicle", vehicle);
            return "redirect:/garage";
		}
		
		if(vehicle.getVehicletype().equals("bike")) {
			vehicle.setNumofdoors(0);
			vehicle.setCarryingcapacity(0);
		} else if(vehicle.getVehicletype().equals("car")) {
			vehicle.setMaxleanangle(0);
			vehicle.setCarryingcapacity(0);
		} else if(vehicle.getVehicletype().equals("lorry")) {
			vehicle.setMaxleanangle(0);
		}
		
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@GetMapping("/vehicles")
	public String getIndex(ModelMap model) {
		List<Vehicle> list = vehicleService.listAll();
		model.addAttribute("vehicles", list);
		model.addAttribute("isAdmin", true);
		model.addAttribute("noData", list.isEmpty());
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
