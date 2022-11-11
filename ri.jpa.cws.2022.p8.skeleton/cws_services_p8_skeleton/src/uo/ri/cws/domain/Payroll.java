package uo.ri.cws.domain;

import java.time.LocalDate;

import javax.persistence.*;

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
	ArgumentChecks.isNotNull(contract,
		"El contrato de la nómina no puede ser null");
	this.contract = contract;
	this.date = LocalDate.now();
    }

    public Payroll(Contract contract, LocalDate date) {
	this(contract);
	ArgumentChecks.isNotNull(date,
		"La fecha de la nómina no puede ser null");
	this.date = date;
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

}
