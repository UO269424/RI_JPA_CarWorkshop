package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

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
    private double annualWage;
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

    public Contract()	{
	
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
	    double settlement, double annualWage, ContractState state) {
	this(mechanic, startDate);
	ArgumentChecks.isNotNull(state, "El estado no puede ser null");
	this.endDate = endDate;
	this.settlement = settlement;
	this.annualWage = annualWage;
	this.state = state;
    }

    public Contract(Mechanic mechanic, ContractType type,
	    ProfessionalGroup group, double d) {
	this(mechanic, LocalDate.now());
	ArgumentChecks.isNotNull(group,
		"El grupo porfesional no puede ser null");
	this.professionalGroup = group;
	this.annualWage = d;
    }
    
    public Contract(Mechanic mechanic, ContractType type,
	    ProfessionalGroup group, LocalDate startDate, double wage) {
	this(mechanic, startDate);
	ArgumentChecks.isNotNull(type, "El tipo de contrato no puede ser null");
	ArgumentChecks.isNotNull(group,
		"El grupo porfesional no puede ser null");
	this.annualWage = wage;
    }

    public enum ContractState {
	TERMINATED, IN_FORCE

    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public LocalDate getEndDate() {
	return endDate;
    }

    public double getSettlement() {
	return settlement;
    }

    public double getAnnualWage() {
	return annualWage;
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

    public ProfessionalGroup getProfesionalGroup() {
	return professionalGroup;
    }

    public ContractType getContractType() {
	return contractType;
    }

    public double getAnnualBaseWage() {
	return annualWage;
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

    public void setAnnualWage(double annualWage) {
        this.annualWage = annualWage;
    }

    public void setState(ContractState state) {
        this.state = state;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public void setProfesionalGroup(ProfessionalGroup profesionalGroup) {
        this.professionalGroup = profesionalGroup;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
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
    
    

}
