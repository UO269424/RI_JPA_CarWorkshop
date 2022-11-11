package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

public class UpdateMechanic implements Command<Void> {

    private MechanicDto dto;

    public UpdateMechanic(MechanicDto dto) {
	ArgumentChecks.isNotNull(dto,
		"El mecánico a actualizar no puede ser null");
	ArgumentChecks.isNotNull(dto.id, "El id no puede ser null");
	ArgumentChecks.isNotEmpty(dto.id.trim(), "El id no puede estar vacío");
	ArgumentChecks.isNotNull(dto.dni, "El dni no puede ser null");
	ArgumentChecks.isNotEmpty(dto.dni.trim(),
		"El dni no puede estar vacío");
	ArgumentChecks.isNotNull(dto.name, "El nombre no puede ser null");
	ArgumentChecks.isNotEmpty(dto.name.trim(),
		"El nombre no puede estar vacío");
	ArgumentChecks.isNotNull(dto.surname, "El apellido no puede ser null");
	ArgumentChecks.isNotEmpty(dto.surname.trim(),
		"El apellido no puede estar vacío");
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
