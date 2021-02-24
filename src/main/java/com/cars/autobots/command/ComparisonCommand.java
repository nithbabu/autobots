package com.cars.autobots.command;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ComparisonCommand {
	private List<String> modelnames;
	private String property;
	public List<String> getModelnames() {
		return modelnames;
	}
	public void setModelnames(List<String> modelnames) {
		this.modelnames = modelnames;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	
}
