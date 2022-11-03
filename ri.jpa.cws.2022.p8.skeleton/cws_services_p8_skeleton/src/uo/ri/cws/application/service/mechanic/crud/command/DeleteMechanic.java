package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class DeleteMechanic implements Command<Void>{

    private String mechanicId;

    public DeleteMechanic(String mechanicId) {
	this.mechanicId = mechanicId;
    }

    public Void execute() throws BusinessException {

	Optional<Mechanic> om = Factory.repository.forMechanic().findById(mechanicId);
	BusinessChecks.exists(om, "The mechanic does not exist");
	Mechanic m = om.get();

	Factory.repository.forMechanic().remove(m);

	return null;
    }

}
