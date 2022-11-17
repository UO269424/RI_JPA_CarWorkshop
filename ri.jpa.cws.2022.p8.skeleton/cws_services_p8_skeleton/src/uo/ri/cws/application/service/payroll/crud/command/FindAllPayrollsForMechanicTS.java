package uo.ri.cws.application.service.payroll.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que devuelve la lista de los
 * resúmenes de todas las nóminas de un mecánico identificado por su id.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindAllPayrollsForMechanicTS
	implements Command<List<PayrollSummaryBLDto>> {

    String mechanicId;

    public FindAllPayrollsForMechanicTS(String mechanicId) {
	ArgumentChecks.isNotNull(mechanicId,
		"El id del mecánico no puede ser nulo");
	ArgumentChecks.isNotEmpty(mechanicId.trim(),
		"El id del mecánico no pueede estar vacío");
	this.mechanicId = mechanicId;
    }

    /**
     * @return lista de todos los {@link PayrollSummaryBLDto} de un mecánico.
     * 
     * @throws BusinessException
     */
    @Override
    public List<PayrollSummaryBLDto> execute() throws BusinessException {
	Optional<Mechanic> om = Factory.repository.forMechanic()
		.findById(mechanicId);
	BusinessChecks.exists(om, "El mecánico no existe");

	Mechanic mechanic = om.get();

	List<Contract> contracts = Factory.repository.forContract()
		.findByMechanicId(mechanic.getId());

	List<Payroll> payrolls = new ArrayList<>();

	for (Contract c : contracts) {
	    List<Payroll> p = Factory.repository.forPayroll()
		    .findByContract(c.getId());
	    payrolls.addAll(p);
	}

//	if (payrolls.isEmpty())
//	    return null;

	return DtoAssembler.toPayrollSummaryBLDtoList(payrolls);
    }

}
