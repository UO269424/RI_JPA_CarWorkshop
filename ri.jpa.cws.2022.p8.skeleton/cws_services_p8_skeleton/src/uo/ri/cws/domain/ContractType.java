package uo.ri.cws.domain;

import java.util.Objects;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TCONTRACTTYPES")
public class ContractType extends BaseEntity {

    @Column(unique = true)
    private String name;
    private double compensationDays;

    public ContractType() {

    }

    public ContractType(String name, double compensationDays) {
	ArgumentChecks.isNotNull(name,
		"El nombre del tipo de contrato no puede ser null");
	ArgumentChecks.isNotBlank(name.trim(),
		"El nombre del tipo de contrato no puede estar vacío");
	ArgumentChecks.isNotNull(compensationDays,
		"Los días de compensación no pueden ser null");
	ArgumentChecks.isTrue(compensationDays >= 0,
		"Los días de compensación no pueden ser negativos");
	this.name = name;
	this.compensationDays = compensationDays;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getCompensationDays() {
	return compensationDays;
    }

    public void setCompensationDays(double compensationDays) {
	this.compensationDays = compensationDays;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(compensationDays, name);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ContractType other = (ContractType) obj;
	return Double.doubleToLongBits(compensationDays) == Double
		.doubleToLongBits(other.compensationDays)
		&& Objects.equals(name, other.name);
    }
    
    

}
