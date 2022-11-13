package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import javax.persistence.Persistence;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que actualiza el tipo de contrato
 * identificado por su nombre
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class UpdateContractTypeTS implements Command<Void> {

    public ContractTypeDto dto;

    public UpdateContractTypeTS(ContractTypeDto dto) {
	ArgumentChecks.isNotNull(dto, "EL dto no puede ser nulo");
	ArgumentChecks.isNotNull(dto.name,
		"EL nombre del tipo de contrato nopuede ser nulo");
	ArgumentChecks.isNotEmpty(dto.name.trim(),
		"EL nombre del tipo de contrato nopuede estar vacío");
	ArgumentChecks.isNotNull(dto.compensationDays,
		"El número de días de compensación no puede ser nulo");
	ArgumentChecks.isTrue(dto.compensationDays >= 0,
		"EL número de días de indemnización no puede ser menor que 0");
	this.dto = dto;
    }

    /**
     * Actualiza el tipo de contrato identificado por su nombre
     * 
     * @throws BusinessException
     */
    @Override
    public Void execute() throws BusinessException {
	Optional<ContractType> op = Factory.repository.forContractType()
		.findByName(dto.name);
	BusinessChecks.exists(op,
		"El tipo de contrato que se intenta actualizar no existe");

//	Factory.repository.forContractType().update;
	return null;
    }

}
