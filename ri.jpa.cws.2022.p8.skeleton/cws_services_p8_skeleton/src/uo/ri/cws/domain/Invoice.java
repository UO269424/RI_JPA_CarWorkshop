package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;
import uo.ri.util.math.Round;

@Entity
@Table(name = "TInvoices")
public class Invoice extends BaseEntity {
    public enum InvoiceState {
	NOT_YET_PAID, PAID
    }

    // natural attributes
    @Column(unique = true)
    private Long number;
    @Basic(optional = false)
    private LocalDate date;
    @Basic(optional = false)
    private double amount;
    private double vat;
    @Enumerated(EnumType.STRING)
    private InvoiceState status = InvoiceState.NOT_YET_PAID;

    // accidental attributes

    @OneToMany(mappedBy = "invoice")
    private Set<WorkOrder> workOrders = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    private Set<Charge> charges = new HashSet<>();

    public Invoice() {
    }

    public Invoice(Long number) {
	this(number, LocalDate.now(), new ArrayList<WorkOrder>());
    }

    public Invoice(Long number, LocalDate date) {
	this(number, date, new ArrayList<WorkOrder>());
    }

    public Invoice(Long number, List<WorkOrder> workOrders) {
	this(number, LocalDate.now(), workOrders);
    }

    // full constructor
    public Invoice(Long number, LocalDate date, List<WorkOrder> workOrders) {
	// check arguments (always), through IllegalArgumentException
	// store the number
	// store a copy of the date
	// add every work order calling addWorkOrder( w )
	ArgumentChecks.isTrue(number >= 0);
	ArgumentChecks.isNotNull(date, "La fecha no puede ser nula");
	ArgumentChecks.isNotNull(workOrders,
		"La lista de workorders no puede ser nula");
	this.number = number;
	this.date = date;
	for (WorkOrder w : workOrders) {
	    ArgumentChecks.isNotNull(w,
		    "Las ordenes de trabajo no pueden ser nulas");
	    addWorkOrder(w);
	}

    }

    public Long getNumber() {
	return number;
    }

    public void setNumber(Long number) {
	this.number = number;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getVat() {
	return vat;
    }

    public void setVat(double vat) {
	this.vat = vat;
    }

    public InvoiceState getStatus() {
	return status;
    }

    public void setStatus(InvoiceState status) {
	this.status = status;
    }

    /**
     * Computes amount and vat (vat depends on the date)
     */
    private void computeAmount() {
	double total = 0;
	for (WorkOrder w : getWorkOrders()) {
	    total += w.getAmount();
	}

	LocalDate vatDate = LocalDate.of(2012, 7, 1);

	if (this.getDate().isBefore(vatDate)) {
	    vat = 1.18;
	} else {
	    vat = 1.21;
	}

	this.amount = Round.twoCents(total * vat);
    }

    /**
     * Adds (double links) the workOrder to the invoice and updates the amount
     * and vat
     * 
     * @param workOrder
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void addWorkOrder(WorkOrder workOrder) {
	StateChecks.isTrue(isNotSettled());
	StateChecks.isTrue(workOrder.isFinished());
	Associations.ToInvoice.link(this, workOrder);
	workOrder.markAsInvoiced();
	computeAmount();
    }

    /**
     * Removes a work order from the invoice and recomputes amount and vat
     * 
     * @param workOrder
     * @see UML_State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void removeWorkOrder(WorkOrder workOrder) {
	StateChecks.isTrue(isNotSettled());
	Associations.ToInvoice.unlink(this, workOrder);
	workOrder.markBackToFinished();
	computeAmount();
    }

    /**
     * Marks the invoice as PAID, but
     * 
     * @throws IllegalStateException if - Is already settled - Or the amounts
     *                               paid with charges to payment means do not
     *                               cover the total of the invoice
     */
    public void settle() {
	StateChecks.isTrue(isNotSettled());
	double total = 0;
	for (Charge c : getCharges()) {
	    total += c.getAmount();
	}
	StateChecks.isTrue(
		total >= this.amount - 0.01 && total <= this.amount + 0.01);
	status = InvoiceState.PAID;
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    public Set<Charge> getCharges() {
	return new HashSet<>(charges);
    }

    Set<Charge> _getCharges() {
	return charges;
    }

    @Override
    public String toString() {
	return "Invoice [number=" + number + ", date=" + date + ", amount="
		+ amount + ", vat=" + vat + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Invoice other = (Invoice) obj;
	return Objects.equals(number, other.number);
    }

    public boolean isNotSettled() {
	return status == InvoiceState.NOT_YET_PAID;
    }

    public boolean isSettled() {
	return !isNotSettled();
    }

}
