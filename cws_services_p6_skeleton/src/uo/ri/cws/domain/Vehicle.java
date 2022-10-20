package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.util.assertion.ArgumentChecks;

public class Vehicle {
	private String plateNumber;
	private String make;
	private String model;
	
	private Client owner;
	private VehicleType type;
	private Set<WorkOrder> workorders;
	
	
	
	


	public Vehicle(String plateNumber, String make, String model) {
	    ArgumentChecks.isNotBlank(plateNumber, "La matrícula no puede estar vacío");
	    ArgumentChecks.isNotBlank(make, "La marca no puede estar vacío");
	    ArgumentChecks.isNotBlank(model, "El modelo no puede estar vacío");
	    this.plateNumber = plateNumber;
	    this.make = make;
	    this.model = model;
	}


	public Vehicle(String plateNumber) {
	    this(plateNumber, "no-make", "no-model");
	}


	
	
	public String getPlateNumber() {
	    return plateNumber;
	}


	public void setPlateNumber(String plateNumber) {
	    this.plateNumber = plateNumber;
	}


	public String getMake() {
	    return make;
	}


	public void setMake(String make) {
	    this.make = make;
	}


	public String getModel() {
	    return model;
	}


	public void setModel(String model) {
	    this.model = model;
	}

	

	public Client getOwner() {
	    return owner;
	}


	void setOwner(Client owner) {
	    this.owner = owner;
	}
	
	


	public VehicleType getType() {
	    return type;
	}


	void setType(VehicleType type) {
	    this.type = type;
	}
	
	public Set<WorkOrder> getWorkorders() {
	    return new HashSet<>(workorders);
	}

	Set<WorkOrder> _getWorkorders() {
	    return workorders;
	}


	@Override
	public String toString() {
	    return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
		    + ", model=" + model + "]";
	}


	@Override
	public int hashCode() {
	    return Objects.hash(make, model, plateNumber);
	}


	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Vehicle other = (Vehicle) obj;
	    return Objects.equals(make, other.make)
		    && Objects.equals(model, other.model)
		    && Objects.equals(plateNumber, other.plateNumber);
	}
	
	
	
	
	
	
	
	

}
