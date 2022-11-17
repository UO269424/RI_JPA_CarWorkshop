package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

public class AddMechanic implements Command<MechanicDto> {

    private MechanicDto dto;

    public AddMechanic(MechanicDto dto) {
	ArgumentChecks.isNotNull(dto, "El dto del mecánico no puede ser null");
	ArgumentChecks.isNotNull(dto.dni,
		"El dni del mecánico no puede ser null");
	this.dto = dto;
    }

    public MechanicDto execute() throws BusinessException {
	if (Factory.repository.forMechanic().findByDni(dto.dni).isPresent())
	    throw new BusinessException(
		    "El mecánico que se quiere añadir ya existe");
	Mechanic m = new Mechanic(dto.dni, dto.name, dto.surname);
	Factory.repository.forMechanic().add(m);
	dto.id = m.getId();
	return dto;
    }

}
