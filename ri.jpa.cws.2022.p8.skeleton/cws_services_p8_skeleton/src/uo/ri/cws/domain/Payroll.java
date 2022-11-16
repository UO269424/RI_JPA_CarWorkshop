package uo.ri.cws.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TPayrolls", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "DATE", "CONTRACT_ID" }) })
public class Payroll extends BaseEntity {

    @Basic(optional = false)
    private LocalDate date;
    private double bonus;
    private double incomeTax;
    private double monthlyWage;
    private double nic;
    private double productivityBonus;
    private double trienniumPayment;

    @ManyToOne()
    private Contract contract;

    public Payroll() {

    }

    public Payroll(Contract contract) {
	this(contract, LocalDate.now());
    }

    public Payroll(Contract contract, LocalDate date) {
	ArgumentChecks.isNotNull(contract,
		"El contrato de a nómina no puede ser null");
	ArgumentChecks.isNotNull(date,
		"La fecha de la nómina no puede ser null");

	Associations.Run.link(contract, this);
	this.date = date;

	this.monthlyWage = calculateMonthlyWage(contract);
	this.bonus = calculateBonus(contract, date);
	this.productivityBonus = calculateProductivityBonus(contract, date);
	this.trienniumPayment = calculateTrienniumPayment(contract, date);
	this.incomeTax = calculateIncomeTax(contract, date);
	this.nic = calculateNic(contract);
    }

    public Payroll(Contract contract2, LocalDate date, double monthlyWage2,
	    double bonus, double productivity, double trienniums, double tax,
	    double nic2) {
	ArgumentChecks.isNotNull(contract2,
		"El contrato de a nómina no puede ser null");
	ArgumentChecks.isNotNull(date,
		"La fecha de la nómina no puede ser null");
	ArgumentChecks.isTrue(bonus >= 0, "El bonus no puede ser menor que 0");
	ArgumentChecks.isTrue(productivity >= 0,
		"El bonus de productividad no puede ser menor que 0");
	ArgumentChecks.isTrue(trienniums >= 0,
		"El trienio no puede ser menor que 0");
	ArgumentChecks.isTrue(tax >= 0, "El IRPF no puede ser menor que 0");
	ArgumentChecks.isTrue(nic2 >= 0, "El nic no puede ser menor que 0");
	Associations.Run.link(contract2, this);
	this.date = date;
	this.bonus = bonus;
	this.productivityBonus = productivity;
	this.trienniumPayment = trienniums;
	this.incomeTax = tax;
	this.nic = nic2;
	this.monthlyWage = monthlyWage2;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public double getBonus() {
	return bonus;
    }

    public void setBonus(double bonus) {
	this.bonus = bonus;
    }

    public double getIncomeTax() {
	return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
	this.incomeTax = incomeTax;
    }

    public double getMonthlyWage() {
	return monthlyWage;
    }

    public void setMonthlyWage(double monthlyWage) {
	this.monthlyWage = monthlyWage;
    }

    public double getNIC() {
	return nic;
    }

    public void setNic(double nic) {
	this.nic = nic;
    }

    public double getProductivityBonus() {
	return productivityBonus;
    }

    public void setProductivityBonus(double productivityBonus) {
	this.productivityBonus = productivityBonus;
    }

    public double getTrienniumPayment() {
	return trienniumPayment;
    }

    public void setTrienniumPayment(double trienniumPayment) {
	this.trienniumPayment = trienniumPayment;
    }

    public Contract getContract() {
	return contract;
    }

    public void setContract(Contract contract) {
	this.contract = contract;
    }

    private double calculateMonthlyWage(Contract con) {
	return con.getAnnualBaseWage() / 14;
    }

    private double calculateNic(Contract con) {
	return (con.getAnnualBaseWage() / 12) * 0.05;
    }

    private double calculateIncomeTax(Contract con, LocalDate date) {
	double annualBaseWage = con.getAnnualBaseWage();
	double gross = calculateMonthlyWage(con) + calculateBonus(con, date)
		+ calculateProductivityBonus(con, date)
		+ calculateTrienniumPayment(con, date);
	if (annualBaseWage <= 12450)
	    return gross * .19;
	if (annualBaseWage > 12450 && annualBaseWage <= 20200)
	    return gross * .24;
	if (annualBaseWage > 20200 && annualBaseWage <= 35200)
	    return gross * .30;
	if (annualBaseWage > 35200 && annualBaseWage <= 60000)
	    return gross * .37;
	if (annualBaseWage > 60000 && annualBaseWage <= 300000)
	    return gross * .45;
	if (annualBaseWage > 300000)
	    return gross * .47;
	return -1;
    }

    private double calculateTrienniumPayment(Contract con, LocalDate date) {
	double payment = con.getProfessionalGroup().getTrienniumPayment();
	int diff = date.getYear() - con.getStartDate().getYear();
	if (con.getStartDate().getMonthValue() > date.getMonthValue() || (con
		.getStartDate().getMonthValue() == date.getMonthValue()
		&& con.getStartDate().getDayOfMonth() > date.getDayOfMonth())) {
	    diff--;
	}

	if (diff < 0)
	    return 0;

	return payment * (diff / 3);
    }

    private double calculateBonus(Contract con, LocalDate date) {
	double monthlyWage = con.getAnnualBaseWage() / 14;
	double bonus = 0;
	if (date.getMonth().equals(Month.JUNE)
		|| date.getMonth().equals(Month.DECEMBER))
	    bonus = monthlyWage;
	return bonus;
    }

    private double calculateProductivityBonus(Contract con, LocalDate date) {

	Mechanic m = con.getMechanic().isEmpty() ? con.getFiredMechanic().get()
		: con.getMechanic().get();

	double productivityPercentage = con.getProfessionalGroup()
		.getProductivityBonusPercentage();
	double totalWorkorders = 0;
	Set<WorkOrder> workorders = m.getAssigned();
	for (WorkOrder wo : workorders) {
	    if (wo.getDate().getMonth().equals(date.getMonth()))
		totalWorkorders += wo.getAmount();
	}

	return totalWorkorders * productivityPercentage / 100;
    }

}
