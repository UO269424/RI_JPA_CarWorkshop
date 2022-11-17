package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TMechanics")
public class Mechanic extends BaseEntity {
    // natural attributes
    @Column(unique = true)
    private String dni;
    @Basic(optional = false)
    private String surname;
    @Basic(optional = false)
    private String name;

    // accidental attributes
    @OneToMany(mappedBy = "mechanic")
    private Set<WorkOrder> assigned = new HashSet<>();

    @OneToMany(mappedBy = "mechanic")
    private Set<Intervention> interventions = new HashSet<>();

    @OneToOne(mappedBy = "mechanic")
    private Contract contractInForce;

    @OneToMany(mappedBy = "firedMechanic")
    private Set<Contract> terminatedContracts = new HashSet<>();

    public Mechanic() {

    }

    public Mechanic(String dni) {
	this(dni, "no-name", "no-surname");
    }

    public Mechanic(String dni, String name, String surname) {
	ArgumentChecks.isNotBlank(dni,
		"El dni del mecánico no puede ser nulo");
	ArgumentChecks.isNotBlank(name,
		"El nombre del mecánico no puede ser nulo");
	ArgumentChecks.isNotBlank(surname,
		"El apellido del mecánico no puede ser nulo");
	this.dni = dni;
	this.surname = surname;
	this.name = name;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<WorkOrder> getAssigned() {
	return new HashSet<>(assigned);
    }

    Set<WorkOrder> _getAssigned() {
	return assigned;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<>(interventions);
    }

    Set<Intervention> _getInterventions() {
	return interventions;
    }

    @Override
    public String toString() {
	return "Mechanic [dni=" + dni + ", surname=" + surname + ", name="
		+ name + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Mechanic other = (Mechanic) obj;
	return Objects.equals(dni, other.dni);
    }

    public Set<Contract> _getTerminatedContracts() {
	return terminatedContracts;
    }

    public Set<Contract> getTerminatedContracts() {
	return new HashSet<>(terminatedContracts);
    }

    public Optional<Contract> getContractInForce() {
	return Optional.ofNullable(this.contractInForce);
    }

    public boolean isInForce() {
	return getContractInForce().isPresent();
    }

    public void setContractInForce(Contract contractInForce) {
	this.contractInForce = contractInForce;
    }

}
