package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.math.Round;

@Entity
@Table(name = "TInterventions", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "WORKORDER_ID", "MECHANIC_ID",
		"DATE" }) })
public class Intervention extends BaseEntity {
    // natural attributes
    @Basic(optional = false)
    private LocalDateTime date;
    private int minutes;

    // accidental attributes
    @ManyToOne
    private WorkOrder workOrder;
    @ManyToOne
    private Mechanic mechanic;
    @OneToMany(mappedBy = "intervention")
    private Set<Substitution> substitutions = new HashSet<>();

    public Intervention() {

    }

    public Intervention(Mechanic mechanic, WorkOrder workOrder,
	    LocalDateTime date, int minutes) {
	ArgumentChecks.isNotNull(mechanic, "Mechanic cant be null");
	ArgumentChecks.isNotNull(workOrder, "Mechanic cant be null");
	ArgumentChecks.isNotNull(date, "Mechanic cant be null");
	ArgumentChecks.isTrue(minutes >= 0, "No puede haber minutos negativos");

	this.date = date.truncatedTo(ChronoUnit.MILLIS);
	this.minutes = minutes;

	Associations.Intervene.link(workOrder, this, mechanic);
    }

    public Intervention(Mechanic mechanic, WorkOrder workorder, int minutes) {
	this(mechanic, workorder, LocalDateTime.now(), minutes);
    }

    void _setWorkOrder(WorkOrder workOrder) {
	this.workOrder = workOrder;
    }

    void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public Set<Substitution> getSubstitutions() {
	return new HashSet<>(substitutions);
    }

    Set<Substitution> _getSubstitutions() {
	return substitutions;
    }

    public LocalDateTime getDate() {
	return date;
    }

    public int getMinutes() {
	return minutes;
    }

    public WorkOrder getWorkOrder() {
	return workOrder;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    public void setDate(LocalDateTime date) {
	this.date = date;
    }

    public void setMinutes(int minutes) {
	this.minutes = minutes;
    }

    public void setWorkOrder(WorkOrder workOrder) {
	this.workOrder = workOrder;
    }

    public void setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public void setSubstitutions(Set<Substitution> substitutions) {
	this.substitutions = substitutions;
    }

    @Override
    public int hashCode() {
	return Objects.hash(date, mechanic, workOrder);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Intervention other = (Intervention) obj;
	return Objects.equals(date, other.date)
		&& Objects.equals(mechanic, other.mechanic)
		&& Objects.equals(workOrder, other.workOrder);
    }

    public double getAmount() {
	double pricePerMinute = getWorkOrder().getVehicle().getVehicleType()
		.getPricePerHour() / 60.0;
	double manufacturePrice = Round.twoCents(pricePerMinute * minutes);
	double substitutionPrice = 0;
	for (Substitution s : getSubstitutions()) {
	    substitutionPrice += s.getAmount();
	}
	return Round.twoCents(substitutionPrice) + manufacturePrice;
    }

}
