package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class UpdateMechanic implements Command<Void>{

    private MechanicDto dto;

    public UpdateMechanic(MechanicDto dto) {
	this.dto = dto;
    }

    public Void execute() throws BusinessException {
	Optional<Mechanic> om = Factory.repository.forMechanic()
		.findById(dto.id);
	BusinessChecks.exists(om, "The mechanic does not exist");

	Mechanic m = om.get();

	BusinessChecks.hasVersion(m, dto.version);

	m.setName(dto.name);
	m.setSurname(dto.surname);

	return null;
    }

}
