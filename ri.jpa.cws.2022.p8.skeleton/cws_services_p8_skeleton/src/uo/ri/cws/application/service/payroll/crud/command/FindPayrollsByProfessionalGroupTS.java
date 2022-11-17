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
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.domain.ProfessionalGroup;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que devuelve la lista de los
 * resúmenes de todas las nóminas de los mecánicos de un grupo porfesional
 * dado.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindPayrollsByProfessionalGroupTS
	implements Command<List<PayrollSummaryBLDto>> {

    String profGroupName;

    public FindPayrollsByProfessionalGroupTS(String profGroupName) {
	ArgumentChecks.isNotNull(profGroupName,
		"El nombre del grupo profesional no puede ser nulo");
	ArgumentChecks.isNotBlank(profGroupName.trim(),
		"El nombre del grupo profesional no puede estar vacío");
	this.profGroupName = profGroupName;
    }

    /**
     * @return lista conteniendo los {@link PayrollSummaryBLDto} de todas las
     *         nóminas de los mecánicos en un grupo porfesional identificado
     *         por su nombre.
     * 
     * @throws BusinessException
     */
    @Override
    public List<PayrollSummaryBLDto> execute() throws BusinessException {
	Optional<ProfessionalGroup> pg = Factory.repository
		.forProfessionalGroup().findByName(profGroupName);
	BusinessChecks.exists(pg, "El grupo profesional no existe");

	List<Contract> contracts = Factory.repository.forContract()
		.findByProfessionalGroupId(pg.get().getId());

	List<Payroll> payrolls = new ArrayList<>();

	for (Contract c : contracts) {
	    List<Payroll> p = Factory.repository.forPayroll()
		    .findByContract(c.getId());
	    payrolls.addAll(p);
	}

	return DtoAssembler.toPayrollSummaryBLDtoList(payrolls);
    }

}
