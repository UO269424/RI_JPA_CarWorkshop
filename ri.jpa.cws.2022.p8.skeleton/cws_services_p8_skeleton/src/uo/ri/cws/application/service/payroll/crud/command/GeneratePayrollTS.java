package uo.ri.cws.application.service.payroll.crud.command;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Contract.ContractState;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.math.Round;

public class GeneratePayrollTS implements Command<Void> {

    private LocalDate date;

    public GeneratePayrollTS(LocalDate date) {
	if (date == null)
	    date = LocalDate.now();
	this.date = date;
    }

    @Override
    public Void execute() throws BusinessException {
	if (this.date == null)
	    return null;
	List<Payroll> newPayrolls = new ArrayList<>();
	List<Contract> allContracts = Factory.repository.forContract()
		.findAll();
	List<Contract> inForceContracts = new ArrayList<>();

	List<Contract> terminated = new ArrayList<>();
	for (Contract c : allContracts) {
	    if (c.getState().equals(ContractState.TERMINATED)) {
		terminated.add(c);
	    } else
		inForceContracts.add(c);
	}
	if (inForceContracts.isEmpty() && terminated.isEmpty())
	    return null;

	List<Contract> thisMonth = new ArrayList<>(terminated);

	for (Contract dto : terminated) {
	    if (dto.getEndDate().isEmpty() || !(dto.getEndDate().get()
		    .getMonth().equals(date.getMonth())
		    && dto.getEndDate().get().getYear() == date.getYear()))
		thisMonth.remove(dto);
	}

	inForceContracts.addAll(thisMonth);

	for (Contract con : inForceContracts) {
	    List<Payroll> payrolls = Factory.repository.forPayroll()
		    .findByContract(con.getId());
	    boolean existsCurrent = false;
	    for (Payroll dto : payrolls) {
		if (dto.getDate().getMonth().equals(date.getMonth())
			&& dto.getDate().getYear() == date.getYear())
		    existsCurrent = true;
	    }

	    if (!existsCurrent) {
		Payroll newPayroll = new Payroll();
		Mechanic m = con.getState().equals(ContractState.TERMINATED)
			? con.getFiredMechanic().get()
			: con.getMechanic().get();

		newPayroll.setDate(date);
		// Earnings
		newPayroll.setMonthlyWage(Round.twoCents(getMonthlyWage(con)));
		newPayroll.setBonus(Round.twoCents(getBonus(con)));
		newPayroll.setProductivityBonus(
			Round.twoCents(getProductivityBonus(con, m)));
		newPayroll.setTrienniumPayment(
			Round.twoCents(getTrienniumPayment(con)));
		double grossWage = newPayroll.getMonthlyWage()
			+ newPayroll.getBonus()
			+ newPayroll.getProductivityBonus()
			+ newPayroll.getTrienniumPayment();
		// Discounts
		newPayroll.setIncomeTax(Round.twoCents(
			getIncomeTax(con.getAnnualBaseWage()) * grossWage));
		newPayroll.setNic(Round.twoCents(getNic(con)));

		newPayroll.setContract(con);

		Factory.repository.forPayroll().add(newPayroll);
		newPayrolls.add(newPayroll);
	    }
	}

	return null;
    }

    private double getMonthlyWage(Contract con) {
	return con.getAnnualBaseWage() / 14;
    }

    private double getNic(Contract con) {
	return (con.getAnnualBaseWage() / 12) * 0.05;
    }

    private double getIncomeTax(double annualBaseWage) {
	if (annualBaseWage <= 12450)
	    return .19;
	if (annualBaseWage > 12450 && annualBaseWage <= 20200)
	    return .24;
	if (annualBaseWage > 20200 && annualBaseWage <= 35200)
	    return .30;
	if (annualBaseWage > 35200 && annualBaseWage <= 60000)
	    return .37;
	if (annualBaseWage > 60000 && annualBaseWage <= 300000)
	    return .45;
	if (annualBaseWage > 300000)
	    return .47;
	return -1;
    }

    private double getTrienniumPayment(Contract con) {
	double payment = Factory.repository.forProfessionalGroup()
		.findByName(con.getProfessionalGroup().getName()).get()
		.getTrienniumPayment();
	int diff = date.getYear() - con.getStartDate().getYear();
	if (con.getStartDate().getMonthValue() > date.getMonthValue() || (con
		.getStartDate().getMonthValue() == date.getMonthValue()
		&& con.getStartDate().getDayOfMonth() > date.getDayOfMonth())) {
	    diff--;
	}

	return payment * (diff / 3);
    }

    private double getBonus(Contract con) {
	double monthlyWage = con.getAnnualBaseWage() / 14;
	double bonus = 0;
	if (date.getMonth().equals(Month.JUNE)
		|| date.getMonth().equals(Month.DECEMBER))
	    bonus = monthlyWage;
	return bonus;
    }

    private double getProductivityBonus(Contract con, Mechanic m) {
	double productivityPercentage = Factory.repository
		.forProfessionalGroup()
		.findByName(con.getProfessionalGroup().getName()).get()
		.getProductivityBonusPercentage();
	double totalWorkorders = 0;
	List<WorkOrder> workorders = Factory.repository.forWorkOrder()
		.findByMechanic(m.getId());
	for (WorkOrder wo : workorders) {
	    if (wo.getDate().getMonth().equals(date.getMonth()))
		totalWorkorders += wo.getAmount();
	}

	return totalWorkorders * productivityPercentage / 100;
    }

}
