package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

@Entity
@Table(name = "TWorkorders", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "DATE", "VEHICLE_ID" }) })
public class WorkOrder extends BaseEntity {
    public enum WorkOrderStatus {
	OPEN, ASSIGNED, FINISHED, INVOICED
    }

    // natural attributes
    @Basic(optional = false)
    private LocalDateTime date;
    @Basic(optional = false)
    private String description;
    private double amount = 0.0;
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status = WorkOrderStatus.OPEN;

    // accidental attributes
    @ManyToOne(optional = false)
    private Vehicle vehicle;
    @ManyToOne()
    private Mechanic mechanic;
    @ManyToOne()
    private Invoice invoice;
    @OneToMany(mappedBy = "workOrder")
    private Set<Intervention> interventions = new HashSet<>();

    public WorkOrder() {

    }

    public WorkOrder(Vehicle vehicle) {
	this(vehicle, LocalDateTime.now(), "no-description");
    }

    public WorkOrder(Vehicle vehicle, String description) {
	this(vehicle);
	ArgumentChecks.isNotEmpty(description.trim());
	this.description = description;
    }

    public WorkOrder(Vehicle vehicle, LocalDateTime date) {
	this(vehicle, date, "no-description");
    }

    public WorkOrder(Vehicle vehicle, LocalDateTime date, String description) {
	ArgumentChecks.isNotNull(vehicle, "El vehículo no puede ser null");
	ArgumentChecks.isNotNull(date, "la fecha no puede ser null");
	ArgumentChecks.isNotBlank(description,
		"La descripción no puede estar vacía");
	this.date = date.truncatedTo(ChronoUnit.MILLIS);
	this.description = description;
	Associations.Fix.link(vehicle, this);
    }

    /**
     * Changes it to INVOICED state given the right conditions This method is
     * called from Invoice.addWorkOrder(...)
     * 
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not FINISHED, or -
     *                               The work order is not linked with the
     *                               invoice
     */
    public void markAsInvoiced() {
	StateChecks.isTrue(isFinished());
	StateChecks.isNotNull(invoice);
	status = WorkOrderStatus.INVOICED;
    }

    /**
     * Changes it to FINISHED state given the right conditions and computes the
     * amount
     *
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED
     *                               state, or - The work order is not linked
     *                               with a mechanic
     */
    public void markAsFinished() {
	StateChecks.isTrue(isAssigned());
	StateChecks.isNotNull(mechanic);
	status = WorkOrderStatus.FINISHED;
	amount = 0;
	for (Intervention i : getInterventions()) {
	    amount += i.getAmount();
	}
    }

    /**
     * Changes it back to FINISHED state given the right conditions This method
     * is called from Invoice.removeWorkOrder(...)
     * 
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not INVOICED, or -
     *                               The work order is still linked with the
     *                               invoice
     */
    public void markBackToFinished() {
	StateChecks.isTrue(isInvoiced());
	StateChecks.isNull(invoice);
	status = WorkOrderStatus.FINISHED;
    }

    /**
     * Links (assigns) the work order to a mechanic and then changes its status
     * to ASSIGNED
     * 
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in OPEN status,
     *                               or - The work order is already linked with
     *                               another mechanic
     */
    public void assignTo(Mechanic mechanic) {
	StateChecks.isTrue(isOpen());
	StateChecks.isNull(this.mechanic);
	Associations.Assign.link(mechanic, this);
	status = WorkOrderStatus.ASSIGNED;
    }

    /**
     * Unlinks (deassigns) the work order and the mechanic and then changes its
     * status back to OPEN
     * 
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED
     *                               status
     */
    public void desassign() {
	StateChecks.isTrue(isAssigned());
	Associations.Assign.unlink(mechanic, this);
	status = WorkOrderStatus.OPEN;
    }

    /**
     * In order to assign a work order to another mechanic is first have to be
     * moved back to OPEN state and unlinked from the previous mechanic.
     * 
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in FINISHED
     *                               status
     */
    public void reopen() {
	StateChecks.isTrue(isFinished());
	status = WorkOrderStatus.OPEN;
	Associations.Assign.unlink(mechanic, this);
    }

    public LocalDateTime getDate() {
	return date;
    }

    public void setDate(LocalDateTime date) {
	this.date = date;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public WorkOrderStatus getStatus() {
	return status;
    }

    public void setStatus(WorkOrderStatus status) {
	this.status = status;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<>(interventions);
    }

    Set<Intervention> _getInterventions() {
	return interventions;
    }

    void _setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    void _setInvoice(Invoice invoice) {
	this.invoice = invoice;
    }

    public Invoice getInvoice() {
	return invoice;
    }

    @Override
    public String toString() {
	return "WorkOrder [date=" + date + ", description=" + description
		+ ", amount=" + amount + ", status=" + status + ", vehicle="
		+ vehicle + ", mechanic=" + mechanic + ", invoice=" + invoice
		+ "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(date, vehicle);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	WorkOrder other = (WorkOrder) obj;
	return Objects.equals(description, other.description)
		&& Objects.equals(vehicle, other.vehicle);
    }

    public boolean isFinished() {
	return this.status.equals(WorkOrderStatus.FINISHED);
    }

    public boolean isInvoiced() {
	return status == WorkOrderStatus.INVOICED;
    }

    private boolean isAssigned() {
	return status == WorkOrderStatus.ASSIGNED;
    }

    private boolean isOpen() {
	return status == WorkOrderStatus.OPEN;
    }

}
