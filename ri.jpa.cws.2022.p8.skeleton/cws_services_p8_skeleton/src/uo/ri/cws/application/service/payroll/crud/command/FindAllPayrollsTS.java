package uo.ri.cws.application.service.payroll.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

/**
 * Implementación de la interfaz Command que devuelve una lista con el resumen
 * de todas las nóminas existentes.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindAllPayrollsTS implements Command<List<PayrollSummaryBLDto>> {

    /**
     * @return lista que contiene todos los {@link PayrollSummaryBLDto}
     */
    @Override
    public List<PayrollSummaryBLDto> execute() throws BusinessException {
	return DtoAssembler.toPayrollSummaryBLDtoList(
		Factory.repository.forPayroll().findAll());
    }

}
