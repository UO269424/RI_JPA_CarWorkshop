package uo.ri.cws.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
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

@Entity
@Table(name = "TContracts", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "STARTDATE", "MECHANIC_ID" }) })
public class Contract extends BaseEntity {

    @Basic(optional = false)
    private LocalDate startDate;
    private LocalDate endDate;
    private double settlement;
    private double annualBaseWage;
    @Enumerated(EnumType.STRING)
    private ContractState state;

    @OneToMany(mappedBy = "contract")
    private Set<Payroll> payrolls = new HashSet<>();
    @ManyToOne()
    private ProfessionalGroup professionalGroup;
    @ManyToOne()
    private ContractType contractType;
    @ManyToOne()
    private Mechanic mechanic;
    @ManyToOne()
    private Mechanic firedMechanic;

    public Contract() {

    }

    public Contract(Mechanic mechanic, LocalDate startDate) {
	ArgumentChecks.isNotNull(mechanic, "EL mecánico no puede ser null");
	ArgumentChecks.isNotNull(startDate,
		"La fecha de inicio no puede ser null");
	this.mechanic = mechanic;
	this.startDate = startDate;
	Associations.Hire.link(this.mechanic, this);
    }

    public Contract(Mechanic mechanic, LocalDate startDate, LocalDate endDate,
	    double settlement, double annualBaseWage, ContractState state) {
	this(mechanic, startDate);
	ArgumentChecks.isNotNull(state, "El estado no puede ser null");
	this.endDate = endDate;
	this.settlement = settlement;
	this.annualBaseWage = annualBaseWage;
	this.state = state;
    }

    public Contract(Mechanic mechanic, ContractType type,
	    ProfessionalGroup group, double d) {
	this(mechanic, LocalDate.now());
	ArgumentChecks.isNotNull(group,
		"El grupo porfesional no puede ser null");
	this.professionalGroup = group;
	this.annualBaseWage = d;

	Associations.Type.link(this, type);
	Associations.Group.link(this, group);
    }

    public Contract(Mechanic mechanic, ContractType type,
	    ProfessionalGroup group, LocalDate startDate, double wage) {
	this(mechanic, startDate);
	ArgumentChecks.isNotNull(type, "El tipo de contrato no puede ser null");
	ArgumentChecks.isNotNull(group,
		"El grupo porfesional no puede ser null");
	this.annualBaseWage = wage;
	Associations.Type.link(this, type);
	Associations.Group.link(this, group);
    }

    public enum ContractState {
	TERMINATED, IN_FORCE

    }

    public Optional<Mechanic> getMechanic() {
	return Optional.ofNullable(mechanic);
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public Optional<LocalDate> getEndDate() {
	return Optional.ofNullable(endDate);
    }

    public double getSettlement() {
	return settlement;
    }

    public double getAnnualBaseWage() {
	return annualBaseWage;
    }

    public ContractState getState() {
	return state;
    }

    public Set<Payroll> getPayrolls() {
	return new HashSet<Payroll>(payrolls);
    }

    Set<Payroll> _getPayrolls() {
	return payrolls;
    }

    public ProfessionalGroup getProfessionalGroup() {
	return professionalGroup;
    }

    public ContractType getContractType() {
	return contractType;
    }

    public void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
    }

    public void setSettlement(double settlement) {
	this.settlement = settlement;
    }

    public void setAnnualBaseWage(double annualBaseWage) {
	this.annualBaseWage = annualBaseWage;
    }

    public void setState(ContractState state) {
	this.state = state;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
	this.payrolls = payrolls;
    }

    public void setProfessionalGroup(ProfessionalGroup profesionalGroup) {
	this.professionalGroup = profesionalGroup;
    }

    public void setContractType(ContractType contractType) {
	this.contractType = contractType;
    }

    public void setFiredMechanic(Mechanic fm) {
	this.firedMechanic = fm;
    }

    public Optional<Mechanic> getFiredMechanic() {
	return Optional.ofNullable(this.firedMechanic);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(mechanic, startDate);
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
	Contract other = (Contract) obj;
	return Objects.equals(mechanic, other.mechanic)
		&& Objects.equals(startDate, other.startDate);
    }

    public void terminate() {
	setEndDate(LocalDate.of(LocalDate.now().getYear(),
		LocalDate.now().getMonth(), LocalDate.now().getMonth()
			.length(LocalDate.now().isLeapYear())));
	if (this.startDate.until(endDate, ChronoUnit.YEARS) >= 1)
	    this.settlement = getIndemnizacion()
		    * getContractType().getCompensationDays()
		    * this.startDate.until(endDate, ChronoUnit.YEARS);
	setState(ContractState.TERMINATED);
	this.mechanic._getTerminatedContracts().add(this);
	Associations.Hire.unlink(this, mechanic);
	Associations.Fire.link(this);
    }

    private double getIndemnizacion() {
	double total = 0;
	for (Payroll p : getPayrolls()) {
	    if (p.getDate().isAfter(LocalDate.now().minus(1, ChronoUnit.YEARS)))
		total += p.getBonus() + p.getMonthlyWage()
			+ p.getTrienniumPayment() + p.getProductivityBonus();
	}
	return total / 365;
    }

}
