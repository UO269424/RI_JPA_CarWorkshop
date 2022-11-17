package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TVehicles")
public class Vehicle extends BaseEntity {
    @Column(unique = true)
    private String plateNumber;
    @Basic(optional = false)
    @Column(name = "BRAND")
    private String make;
    @Basic(optional = false)
    private String model;

    @ManyToOne(optional = false)
    private Client client;
    @ManyToOne(optional = false)
    private VehicleType vehicleType;
    @OneToMany(mappedBy = "vehicle")
    private Set<WorkOrder> workOrders = new HashSet<>();

    public Vehicle(String plateNumber, String make, String model) {
	ArgumentChecks.isNotBlank(plateNumber,
		"La matrícula no puede estar vacío");
	ArgumentChecks.isNotBlank(make, "La marca no puede estar vacío");
	ArgumentChecks.isNotBlank(model, "El modelo no puede estar vacío");
	this.plateNumber = plateNumber;
	this.make = make;
	this.model = model;
    }

    public Vehicle() {

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

    public Client getClient() {
	return client;
    }

    void setOwner(Client owner) {
	this.client = owner;
    }

    public VehicleType getVehicleType() {
	return vehicleType;
    }

    void setVehicleType(VehicleType type) {
	this.vehicleType = type;
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    @Override
    public String toString() {
	return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
		+ ", model=" + model + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(plateNumber);
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
	return Objects.equals(plateNumber, other.plateNumber);
    }

}
