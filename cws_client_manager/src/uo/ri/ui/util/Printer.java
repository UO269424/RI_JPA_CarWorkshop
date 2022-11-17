package uo.ri.ui.util;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.contract.ContractService.ContractDto;
import uo.ri.cws.application.service.contract.ContractService.ContractSummaryDto;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.service.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService.WorkOrderDto;
import uo.ri.util.console.Console;
import uo.ri.util.exception.NotYetImplementedException;

public class Printer {

    public static void printInvoice(InvoiceDto invoice) {

	double importeConIVa = invoice.total;
	double iva = invoice.vat;
	double importeSinIva = importeConIVa / (1 + iva / 100);

	Console.printf("Invoice #: %d\n", invoice.number);
	Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", invoice.date);
	Console.printf("\tTotal: %.2f €\n", importeSinIva);
	Console.printf("\tTax: %.1f %% \n", invoice.vat);
	Console.printf("\tTotal, tax inc.: %.2f €\n", invoice.total);
	Console.printf("\tStatus: %s\n", invoice.state);
    }

    public static void printPaymentMeans(List<PaymentMeanDto> medios) {
	Console.println();
	Console.println("Available payment means");

	Console.printf("\t%s \t%-8.8s \t%s \n", "Id", "Type", "Acummulated");
	for (PaymentMeanDto medio : medios) {
	    printPaymentMean(medio);
	}
    }

    private static void printPaymentMean(PaymentMeanDto medio) {
	Console.printf("\t%s \t%-8.8s \t%s \n", medio.id,
		medio.getClass().getName() // not the best...
		, medio.accumulated);
    }

    public static void printWorkOrder(WorkOrderDto rep) {

	Console.printf("\t%s \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n",
		rep.id, rep.description, rep.date, rep.state, rep.total);
    }

    public static void printMechanic(MechanicDto m) {

	Console.printf("\t%s %-10.10s %-15.15s %-25.25s\n", m.id, m.dni, m.name,
		m.surname);
    }

    public static void printVehicleType(VehicleTypeDto vt) {

	Console.printf("\t%s %-10.10s %5.2f %d\n", vt.id, vt.name,
		vt.pricePerHour, vt.minTrainigHours);
    }

    public static void printContractType(ContractTypeDto result) {
	Console.printf("\t%-36.36s %.2f %-10.10s %-10s\n", result.id,
		result.compensationDays, result.name, result.version);

    }

    public static void printContractTypes(
	    List<ContractTypeDto> findAllContractTypes) {

	Console.printf("\t%-36s %-10s %-10s %-25s %-10s\n",
		"Contract Type identifier", "CompensationDays", "Name",
		"Version");

	for (ContractTypeDto ct : findAllContractTypes)
	    printContractType(ct);

    }

    public static void displayThisContractDetailsWithPayrolls(
	    ContractSummaryDto c) {
	throw new NotYetImplementedException(
		"I didn't have to implement this feature for my use cases");

    }

    public static void displayThisContractDetails(ContractDto contractBLDto) {
	throw new NotYetImplementedException(
		"I didn't have to implement this feature for my use cases");

    }

    public static void displayAllContractsDetailsWithPayrolls(
	    List<ContractSummaryDto> result) {
	throw new NotYetImplementedException(
		"I didn't have to implement this feature for my use cases");

    }

    public static void displayAllContracts() {
	throw new NotYetImplementedException(
		"I didn't have to implement this feature for my use cases");

    }

    public static void printPayrollsSumary(
	    List<PayrollSummaryBLDto> allPayrolls) {
	Console.printf("\t%s \t%s \t%s \t%s", "Payroll Id", "Version", "Date",
		"net wage");

	for (PayrollSummaryBLDto sum : allPayrolls)
	    printPayrollSummary(sum);

    }

    private static void printPayrollSummary(PayrollSummaryBLDto sum) {
	Console.printf("\t%s \t%d \t%s \t%d", sum.id, sum.version,
		sum.date.toString(), sum.netWage);

    }

    public static void printPayrolls(Optional<PayrollBLDto> result) {
	Console.printf("\t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s",
		"Payroll Id", "Version", "ContractId", "Date", "Monthly wage",
		"Bonus", "Productivity bonus", "Triennium Payment",
		"Income tax", "Nic", "Net wage");

	Console.printf("\t%s \t%d \t%s \t%s \t%d \t%d \t%d \t%d \t%d \t%d \t%d",
		result.get().id, result.get().version, result.get().contractId,
		result.get().date.toString(), result.get().monthlyWage,
		result.get().bonus, result.get().productivityBonus,
		result.get().trienniumPayment, result.get().incomeTax,
		result.get().nic, result.get().netWage);

    }

    private static void printProfessionalGroupHeader() {
	Console.printf("\t%s \t%s \t%s \t%s \t%s", "Professional group Id",
		"Name", "Productivity rate", "Triennium Salary", "Version");
    }

    public static void printProfessionalGroup(ProfessionalGroupBLDto pg) {
	printProfessionalGroupHeader();
	Console.printf("\t%s \t%s \t%d \t%d \t%d", pg.id, pg.name,
		pg.productivityRate, pg.trieniumSalary, pg.version);

    }

    public static void printProfessionalGroupList(
	    List<ProfessionalGroupBLDto> list) {
	printProfessionalGroupHeader();
	for (ProfessionalGroupBLDto dto : list)
	    printProfessionalGroup(dto);
    }

}
