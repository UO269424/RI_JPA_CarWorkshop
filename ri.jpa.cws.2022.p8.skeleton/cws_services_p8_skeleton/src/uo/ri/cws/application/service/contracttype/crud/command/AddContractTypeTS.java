package uo.ri.cws.application.service.contracttype.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

public class AddContractTypeTS implements Command<ContractTypeDto> {

    private ContractTypeDto dto;

    public AddContractTypeTS(ContractTypeDto dto) {
	ArgumentChecks.isNotNull(dto,
		"El tipo de contrato a añadir no puede ser null");
	ArgumentChecks.isNotNull(dto.name,
		"El nombre del tipo de contrato no puede ser null");
	ArgumentChecks.isNotBlank(dto.name.trim(),
		"El nombre del tipo de contrato no puede estar vacío");
	ArgumentChecks.isNotNull(dto.compensationDays,
		"Los días de compensación no pueden ser null");
	ArgumentChecks.isTrue(dto.compensationDays >= 0,
		"Los días de compensación no pueden ser menor que 0");
	this.dto = dto;
    }

    @Override
    public ContractTypeDto execute() throws BusinessException {
	if (Factory.repository.forContractType().findByName(dto.name)
		.isPresent())
	    throw new BusinessException(
		    "El tipo de contrato que se quiere añadir ya existe");
	ContractType ct = new ContractType(dto.name, dto.compensationDays);
	Factory.repository.forContractType().add(ct);
	dto.id = ct.getId();
	return dto;
    }

}
