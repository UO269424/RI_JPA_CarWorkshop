package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.util.assertion.ArgumentChecks;

public class Mechanic {
	// natural attributes
	private String dni;
	private String surname;
	private String name;

	// accidental attributes
	private Set<WorkOrder> assigned = new HashSet<>();
	private Set<Intervention> interventions = new HashSet<>();
	
	public Mechanic(String dni)	{
	    this(dni, "no-name", "no-surname");
	}

	public Mechanic(String dni, String name, String surname) {
	    ArgumentChecks.isNotBlank(dni, "El dni del mecánico no puede ser nulo");
	    ArgumentChecks.isNotBlank(name, "El nombre del mecánico no puede ser nulo");
	    ArgumentChecks.isNotBlank(surname, "El apellido del mecánico no puede ser nulo");
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
		return new HashSet<>( assigned );
	}

	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<>( interventions );
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
	    return Objects.hash(dni, name, surname);
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
	    return Objects.equals(dni, other.dni)
		    && Objects.equals(name, other.name)
		    && Objects.equals(surname, other.surname);
	}
	
	

}
