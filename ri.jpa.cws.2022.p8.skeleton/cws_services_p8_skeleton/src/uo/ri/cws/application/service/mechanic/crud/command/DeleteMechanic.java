package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class DeleteMechanic implements Command<Void> {

    private String mechanicId;

    public DeleteMechanic(String mechanicId) {
	ArgumentChecks.isNotNull(mechanicId,
		"El id del mecánico no puede ser null");
	ArgumentChecks.isNotEmpty(mechanicId.trim(),
		"El id del mecánico no puede estar vacío");
	this.mechanicId = mechanicId;
    }

    public Void execute() throws BusinessException {
	Optional<Mechanic> om = Factory.repository.forMechanic()
		.findById(mechanicId);
	BusinessChecks.exists(om, "The mechanic does not exist");
	Mechanic mechanic = om.get();

	// Check the mechanic has workorders assigned
	if (hasWorkorders(mechanic.getId())) {
	    throw new BusinessException("The mechanic has workorders assigned");
	}

	// Check the mechanic has contracts assigned
	if (hasContracts(mechanic.getId())) {
	    throw new BusinessException("The mechanic has contracts assigned");
	}

	Factory.repository.forMechanic().remove(mechanic);

	return null;
    }

    private boolean hasWorkorders(String mechanic_id) {
	List<WorkOrder> assigned = Factory.repository.forWorkOrder()
		.findByMechanic(mechanic_id);
	return !assigned.isEmpty();
    }

    private boolean hasContracts(String mechanic_id) throws BusinessException {
	List<Contract> contracts = Factory.repository.forContract().findAll();
	boolean hasContracts = false;
	for (Contract c : contracts) {
	    if ((c.getMechanic() == null
		    && c.getFiredMechanic().get().getId().equals(mechanic_id))
		    || (c.getFiredMechanic() == null
			    && c.getMechanic().get().getId().equals(mechanic_id)))
		hasContracts = true;
	}
	return hasContracts;
    }

}
