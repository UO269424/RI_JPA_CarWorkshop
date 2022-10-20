package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.util.assertion.ArgumentChecks;

public class WorkOrder {
	public enum WorkOrderStatus {
		OPEN,
		ASSIGNED,
		FINISHED,
		INVOICED
	}

	// natural attributes
	private LocalDateTime date;
	private String description;
	private double amount = 0.0;
	private WorkOrderStatus status = WorkOrderStatus.OPEN;

	// accidental attributes
	private Vehicle vehicle;
	private Mechanic mechanic;
	private Invoice invoice;
	private Set<Intervention> interventions = new HashSet<>();
	
	
	public WorkOrder(Vehicle vehicle) {
	    this(vehicle, LocalDateTime.now(), "no-description");
	}

	public WorkOrder(Vehicle vehicle, LocalDateTime date) {
	    this(vehicle, date, "no-description");
	}

	public WorkOrder(Vehicle vehicle, LocalDateTime date, String description) {
	    ArgumentChecks.isNotNull(vehicle, "El vehículo no puede ser null");
	    ArgumentChecks.isNotNull(date, "la fecha no puede ser null");
	    ArgumentChecks.isNotBlank(description, "La descripción no puede estar vacía");
	    this.date = date.truncatedTo(ChronoUnit.MILLIS);
	    this.description = description;
	    Associations.Fix.link(vehicle, this);
	}

	/**
	 * Changes it to INVOICED state given the right conditions
	 * This method is called from Invoice.addWorkOrder(...)
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not FINISHED, or
	 *  - The work order is not linked with the invoice
	 */
	public void markAsInvoiced() {

	}

	/**
	 * Changes it to FINISHED state given the right conditions and
	 * computes the amount
	 *
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED state, or
	 *  - The work order is not linked with a mechanic
	 */
	public void markAsFinished() {

	}

	/**
	 * Changes it back to FINISHED state given the right conditions
	 * This method is called from Invoice.removeWorkOrder(...)
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not INVOICED, or
	 *  - The work order is still linked with the invoice
	 */
	public void markBackToFinished() {

	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its status
	 * to ASSIGNED
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in OPEN status, or
	 *  - The work order is already linked with another mechanic
	 */
	public void assignTo(Mechanic mechanic) {

	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes
	 * its status back to OPEN
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED status
	 */
	public void desassign() {

	}

	/**
	 * In order to assign a work order to another mechanic is first have to
	 * be moved back to OPEN state and unlinked from the previous mechanic.
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in FINISHED status
	 */
	public void reopen() {

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
		return new HashSet<>( interventions );
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

	@Override
	public String toString() {
	    return "WorkOrder [date=" + date + ", description=" + description
		    + ", amount=" + amount + ", status=" + status + ", vehicle="
		    + vehicle + ", mechanic=" + mechanic + ", invoice="
		    + invoice + "]";
	}

	@Override
	public int hashCode() {
	    return Objects.hash(amount, date, description, status);
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
	    return Double.doubleToLongBits(amount) == Double
		    .doubleToLongBits(other.amount)
		    && Objects.equals(date, other.date)
		    && Objects.equals(description, other.description)
		    && status == other.status;
	}
	
	

}
