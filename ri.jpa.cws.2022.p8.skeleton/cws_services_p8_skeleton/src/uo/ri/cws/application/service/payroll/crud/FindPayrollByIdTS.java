package uo.ri.cws.application.service.payroll.crud;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que devuelve una nómina identificada
 * por su id.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindPayrollByIdTS implements Command<Optional<PayrollBLDto>> {

    String payrollid;

    public FindPayrollByIdTS(String payrollid) {
	ArgumentChecks.isNotNull(payrollid,
		"El id de la nómina no puede ser nulo");
	ArgumentChecks.isNotBlank(payrollid.trim(),
		"El id de la nómina no puede estar vacío");
	this.payrollid = payrollid;
    }

    /**
     * @return {@link Optional} que contiiene el {@link PayrollBLDto}
     *         identificado por payrollid
     * 
     * @throws BusinessException
     */
    @Override
    public Optional<PayrollBLDto> execute() throws BusinessException {
	Optional<Payroll> op = Factory.repository.forPayroll()
		.findById(payrollid);
	if(op.isEmpty())
	    return Optional.empty();
	return Optional.of(DtoAssembler.toPayrollBLDto(op.get()));
    }

}
