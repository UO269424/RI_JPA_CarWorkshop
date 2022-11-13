package uo.ri.cws.application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.client.ClientCrudService.ClientDto;
import uo.ri.cws.application.service.contract.ContractService.ContractSummaryDto;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.service.invoice.InvoicingService.CardDto;
import uo.ri.cws.application.service.invoice.InvoicingService.CashDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.invoice.InvoicingService.VoucherDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.service.vehicle.VehicleCrudService.VehicleDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService.WorkOrderDto;
import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.Voucher;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.math.Round;

public class DtoAssembler {

    public static ClientDto toDto(Client c) {
	ClientDto dto = new ClientDto();

	dto.id = c.getId();
	dto.version = c.getVersion();

	dto.dni = c.getDni();
	dto.name = c.getName();
	dto.surname = c.getSurname();

	return dto;
    }

    public static List<ClientDto> toClientDtoList(List<Client> clientes) {
	List<ClientDto> res = new ArrayList<>();
	for (Client c : clientes) {
	    res.add(DtoAssembler.toDto(c));
	}
	return res;
    }

    public static MechanicDto toDto(Mechanic m) {
	MechanicDto dto = new MechanicDto();
	dto.id = m.getId();
	dto.version = m.getVersion();

	dto.dni = m.getDni();
	dto.name = m.getName();
	dto.surname = m.getSurname();
	return dto;
    }

    public static List<MechanicDto> toMechanicDtoList(List<Mechanic> list) {
	List<MechanicDto> res = new ArrayList<>();
	for (Mechanic m : list) {
	    res.add(toDto(m));
	}
	return res;
    }

    public static List<VoucherDto> toVoucherDtoList(List<Voucher> list) {
	List<VoucherDto> res = new ArrayList<>();
	for (Voucher b : list) {
	    res.add(toDto(b));
	}
	return res;
    }

    public static VoucherDto toDto(Voucher v) {
	VoucherDto dto = new VoucherDto();
	dto.id = v.getId();
	dto.version = v.getVersion();

	dto.clientId = v.getClient().getId();
	dto.accumulated = v.getAccumulated();
	dto.code = v.getCode();
	dto.description = v.getDescription();
	dto.available = v.getAvailable();
	return dto;
    }

    public static CardDto toDto(CreditCard cc) {
	CardDto dto = new CardDto();
	dto.id = cc.getId();
	dto.version = cc.getVersion();

	dto.clientId = cc.getClient().getId();
	dto.accumulated = cc.getAccumulated();
	dto.cardNumber = cc.getNumber();
	dto.cardExpiration = cc.getValidThru();
	dto.cardType = cc.getType();
	return dto;
    }

    public static CashDto toDto(Cash m) {
	CashDto dto = new CashDto();
	dto.id = m.getId();
	dto.version = m.getVersion();

	dto.clientId = m.getClient().getId();
	dto.accumulated = m.getAccumulated();
	return dto;
    }

    public static InvoiceDto toDto(Invoice invoice) {
	InvoiceDto dto = new InvoiceDto();
	dto.id = invoice.getId();
	dto.version = invoice.getVersion();

	dto.number = invoice.getNumber();
	dto.date = invoice.getDate();
	dto.total = invoice.getAmount();
	dto.vat = invoice.getVat();
	dto.state = invoice.getStatus().toString();
	return dto;
    }

    public static List<PaymentMeanDto> toPaymentMeanDtoList(
	    List<PaymentMean> list) {
	return list.stream().map(mp -> toDto(mp)).collect(Collectors.toList());
    }

    private static PaymentMeanDto toDto(PaymentMean mp) {
	if (mp instanceof Voucher) {
	    return toDto((Voucher) mp);
	} else if (mp instanceof CreditCard) {
	    return toDto((CreditCard) mp);
	} else if (mp instanceof Cash) {
	    return toDto((Cash) mp);
	} else {
	    throw new RuntimeException("Unexpected type of payment mean");
	}
    }

    public static WorkOrderDto toDto(WorkOrder a) {
	WorkOrderDto dto = new WorkOrderDto();
	dto.id = a.getId();
	dto.version = a.getVersion();

	dto.vehicleId = a.getVehicle().getId();
	dto.description = a.getDescription();
	dto.date = a.getDate();
	dto.total = a.getAmount();
	dto.state = a.getStatus().toString();

	dto.invoiceId = a.getInvoice() == null ? null : a.getInvoice().getId();

	return dto;
    }

    public static VehicleDto toDto(Vehicle v) {
	VehicleDto dto = new VehicleDto();
	dto.id = v.getId();
	dto.version = v.getVersion();

	dto.plate = v.getPlateNumber();
	dto.clientId = v.getClient().getId();
	dto.make = v.getMake();
	dto.vehicleTypeId = v.getVehicleType().getId();
	dto.model = v.getModel();

	return dto;
    }

    public static List<WorkOrderDto> toWorkOrderDtoList(List<WorkOrder> list) {
	return list.stream().map(a -> toDto(a)).collect(Collectors.toList());
    }

    public static VehicleTypeDto toDto(VehicleType vt) {
	VehicleTypeDto dto = new VehicleTypeDto();

	dto.id = vt.getId();
	dto.version = vt.getVersion();

	dto.name = vt.getName();
	dto.pricePerHour = vt.getPricePerHour();

	return dto;
    }

    public static List<VehicleTypeDto> toVehicleTypeDtoList(
	    List<VehicleType> list) {
	return list.stream().map(a -> toDto(a)).collect(Collectors.toList());
    }

    /*
     * ----------------------------------------------------------------------
     * AMPLIACIÓN
     * ----------------------------------------------------------------------
     */

    public static ContractSummaryDto toContractSummaryDto(Contract c) {
	ContractSummaryDto summaryDto = new ContractSummaryDto();

	summaryDto.dni = c.getMechanic().getDni();
	summaryDto.id = c.getId();
	summaryDto.numPayrolls = c.getPayrolls().size();
	summaryDto.settlement = c.getSettlement();
	summaryDto.state = c.getState();
	summaryDto.version = c.getVersion();

	return summaryDto;
    }

    public static List<ContractSummaryDto> toContractSummaryDtoList(
	    List<Contract> contractList) {
	List<ContractSummaryDto> lista = new ArrayList<>();
	for (Contract c : contractList)
	    lista.add(toContractSummaryDto(c));
	return lista;
    }

    public static List<PayrollSummaryBLDto> toPayrollSummaryBLDtoList(
	    List<Payroll> payrollList) {
	List<PayrollSummaryBLDto> lista = new ArrayList<>();
	for (Payroll p : payrollList)
	    lista.add(toPayrollSummaryBLDto(p));
	return lista;
    }

    private static PayrollSummaryBLDto toPayrollSummaryBLDto(Payroll p) {
	PayrollSummaryBLDto dto = new PayrollSummaryBLDto();

	dto.id = p.getId();
	dto.date = p.getDate();
	dto.netWage = p.getBonus() + p.getProductivityBonus()
		+ p.getTrienniumPayment() + p.getMonthlyWage()
		- p.getIncomeTax() - p.getNIC();
	dto.version = p.getVersion();

	return dto;
    }

    public static PayrollBLDto toPayrollBLDto(Payroll p) {
	PayrollBLDto dto = new PayrollBLDto();

	dto.id = p.getId();
	dto.bonus = p.getBonus();
	dto.date = p.getDate();
	dto.incomeTax = p.getIncomeTax();
	dto.monthlyWage = p.getMonthlyWage();
	dto.nic = p.getNIC();
	dto.productivityBonus = p.getProductivityBonus();
	dto.trienniumPayment = p.getTrienniumPayment();
	dto.version = p.getVersion();
	dto.contractId = p.getContract().getId();
	dto.netWage = getNetWage(p);

	return dto;
    }

    public static double getNetWage(Payroll p) {
	return Round.twoCents(p.getBonus() + p.getProductivityBonus()
		+ p.getTrienniumPayment() + p.getMonthlyWage()
		- p.getIncomeTax() - p.getNIC());
    }

    public static List<ContractTypeDto> toContractTypeDtoList(
	    List<ContractType> lista) {
	List<ContractTypeDto> dtos = new ArrayList<ContractTypeDto>();
	for (ContractType ct : lista) {
	    dtos.add(toContractTypeDto(ct));
	}
	return dtos;
    }

    public static ContractTypeDto toContractTypeDto(ContractType ct) {
	ContractTypeDto dto = new ContractTypeDto();
	dto.id = ct.getId();
	dto.name = ct.getName();
	dto.compensationDays = ct.getCompensationDays();
	dto.version = ct.getVersion();
	return dto;
    }

}
