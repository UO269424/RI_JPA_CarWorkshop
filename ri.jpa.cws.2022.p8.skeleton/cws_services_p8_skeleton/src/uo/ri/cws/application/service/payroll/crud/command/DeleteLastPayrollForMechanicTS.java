package uo.ri.cws.application.service.payroll.crud.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

public class DeleteLastPayrollForMechanicTS implements Command<Void> {

    private String mechanicId;

    public DeleteLastPayrollForMechanicTS(String mechanicId) {
	ArgumentChecks.isNotNull(mechanicId, "El id no puede ser null");
	ArgumentChecks.isNotBlank(mechanicId, "El id no puede estar vacío");
	this.mechanicId = mechanicId;
    }

    /**
     * Elimina la nómina generada en el mes actual para el mecánico identificado
     * 
     * @throws BusinessException
     */
    @Override
    public Void execute() throws BusinessException {

	Optional<Mechanic> mechanic = Factory.repository.forMechanic()
		.findById(mechanicId);

	BusinessChecks.exists(mechanic,
		"El mecánico para el que se quieren eliminar las nóminas "
		+ "no existe");

	List<Contract> contracts = Factory.repository.forContract()
		.findByMechanicId(mechanic.get().getId());

	List<Payroll> payrolls = new ArrayList<>();

	for (Contract c : contracts) {
	    List<Payroll> p = Factory.repository.forPayroll()
		    .findByContract(c.getId());
	    payrolls.addAll(p);
	}

	if (payrolls.isEmpty())
	    return null;

	LocalDate present = LocalDate.now();

	for (Payroll p : payrolls) {
	    if (p.getDate().getMonth().equals(present.getMonth())
		    && p.getDate().getYear() == present.getYear())
		Factory.repository.forPayroll().remove(p);
	}

	return null;
    }

}
